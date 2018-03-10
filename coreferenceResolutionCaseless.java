import java.io.*;
import java.util.*;

import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.*;

public class coreferenceResolutionCaseless {
    public static void main(String[] args) throws Exception {

        PrintWriter out = new PrintWriter("outFile.txt");
        PrintWriter xmlOut = new PrintWriter("outFile.xml");

        Annotation document = new Annotation("like many heartland states , iowa has had trouble keeping young people down on the farm or anywhere within state lines .\n" +
                "with population waning , the state is looking beyond its borders for newcomers .\n" +
                "as abc's jim sciutto reports , one little town may provide a big lesson .\n" +
                "on homecoming night postville feels like hometown , usa , but a look around this town of 2,000 shows it's become a miniature ellis island .\n" +
                "this was an all - white , all - christian community that all the sudden was taken over -- not taken over , that's a very bad choice of words , but invaded by , perhaps different groups .\n" +
                "it began when a hasidic jewish family bought one of the town's two meat - packing plants 13 years ago .\n" +
                "first they brought in other hasidic jews , then mexicans , palestinians , ukrainians .\n" +
                "the plant and another next door changed the face of postville .\n" +
                "i think postville might be catching up with the rest of america .\n" +
                "if you look all over america , if you did a study of any great city , you would see the same thing .\n" +
                "the plants helped spur economic development in a town that had long been stagnant .\n" +
                "stores are expanding .\n" +
                "new apartments and houses are being built , and there are hundreds of new jobs , making unemployment almost nonexistent .\n" +
                "what else would you like ?\n" +
                "postville now has 22 different nationalities , but some residents have n't welcomed the newcomers .\n" +
                "they have no insurance on their cars .\n" +
                "they do n't stop at stop signs .\n" +
                "they do not put their children in a cash -- they've never heard of a car seat .\n" +
                "you know we've been here a long time and i think most people would meet them halfway , but that does n't seem to be what they want .\n" +
                "for those who prefer the old postville , mayor john hyman has a simple answer .\n" +
                "i just tell them , you've got to change .\n" +
                "they're here to stay .\n" +
                "they're not gon na leave , and you're going to have to adapt to these people .\n" +
                "iowa's governor is now looking to follow postville's example , relaxing immigration quotas .\n" +
                "it's a goal that seems possible when on homecoming night the starting kicker is 14 - year - old nikita kargalskiy , who may be 5,000 miles from his hometown in russia .\n" +
                "jim sciutto , abc news , postville iowa .");
        Properties caselessProps = new Properties();
        caselessProps.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        caselessProps.put( "pos.model", "english-caseless-left3words-distsim.tagger");
        caselessProps.put( "parse.model", "englishPCFG.caseless.ser.gz" );
        caselessProps.put( "ner.model.3class", "english.all.3class.caseless.distsim.crf.ser.gz");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(caselessProps);
        Annotation annotation = new Annotation(document);
        pipeline.annotate(annotation);

        pipeline.prettyPrint(annotation, out);
        if (xmlOut != null) {
            pipeline.xmlPrint(annotation, xmlOut);
        }

        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        if (sentences != null && !sentences.isEmpty()) {
            System.out.println("Coreference information");
            Map<Integer, CorefChain> corefChains =
                    annotation.get(CorefCoreAnnotations.CorefChainAnnotation.class);
            if (corefChains == null) {
                return;
            }
            for (Map.Entry<Integer, CorefChain> entry : corefChains.entrySet()) {
                System.out.println("Chain " + entry.getKey());
                for (CorefChain.CorefMention m : entry.getValue().getMentionsInTextualOrder()) {
                    // We need to subtract one since the indices count from 1 but the Lists start from 0
                    List<CoreLabel> tokens = sentences.get(m.sentNum - 1).get(CoreAnnotations.TokensAnnotation.class);
                    // We subtract two for end: one for 0-based indexing, and one because we want last token of mention not one following.
                    System.out.println("  " + m + ", i.e., 0-based character offsets [" + tokens.get(m.startIndex - 1).beginPosition() +
                            ", " + tokens.get(m.endIndex - 2).endPosition() + ")");
                }
            }
            System.out.println();
        }
        IOUtils.closeIgnoringExceptions(out);
        IOUtils.closeIgnoringExceptions(xmlOut);
    }
}