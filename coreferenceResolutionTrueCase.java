import java.io.*;
import java.util.*;

import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.*;

public class coreferenceResolutionTrueCase {
    public static void main(String[] args) throws Exception {

        PrintWriter out = new PrintWriter("outFile.txt");
        PrintWriter xmlOut = new PrintWriter("outFile.xml");

        Annotation document = new Annotation("Devastating Critique of the Arab World by One of Its Own .\n" +
                "Thanks again to my friend Nick Keck for passing this along via e-mail .\n" +
                "It is the text of a speech given by an Arab gentleman before the advisory board of a large multi-nation corporation .\n" +
                "It's a long but very worthwhile read .\n" +
                "A View from the Eye of the Storm .\n" +
                "Talk delivered by Haim Harari at a meeting of the International Advisory Board of a large multi-national corporation , April , 2004 .\n" +
                "As you know , I usually provide the scientific and technological entertainment in our meetings , but , on this occasion , our Chairman suggested that I present my own personal view on events in the part of the world from which I come .\n" +
                "I have never been and I will never be a Government official and I have no privileged information .\n" +
                "My perspective is entirely based on what I see , on what I read and on the fact that my family has lived in this region for almost 200 years .\n" +
                "You may regard my views as those of the proverbial taxi driver , which you are supposed to question , when you visit a country .\n" +
                "I could have shared with you some fascinating facts and some personal thoughts about the Israeli - Arab conflict .\n" +
                "However , I will touch upon it only in passing .\n" +
                "I prefer to devote most of my remarks to the broader picture of the region and its place in world events .\n" +
                "I refer to the entire area between Pakistan and Morocco , which is predominantly Arab , predominantly Moslem , but includes many non-Arab and also significant non-Moslem minorities .\n" +
                "Why do I put aside Israel and its own immediate neighborhood ?\n" +
                "Because Israel and any problems related to it , in spite of what you might read or hear in the world media , is not the central issue , and has never been the central issue in the upheaval in the region .\n" +
                "Yes , there is a 100 year - old Israeli - Arab conflict .\n" +
                "but it is not where the main show is .\n" +
                "The millions who died in the Iran - Iraq war had nothing to do with Israel .\n" +
                "The mass murder happening right now in Sudan , where the Arab Moslem regime is massacring its black Christian citizens , has nothing to do with Israel .\n" +
                "The frequent reports from Algeria about the murders of hundreds of civilians in one village or another by other Algerians have nothing to do with Israel .\n" +
                "Saddam Hussein did not invade Kuwait , endanger Saudi Arabia and butcher his own people because of Israel .\n" +
                "Egypt did not use poison gas against Yemen in the 60's because of Israel .\n" +
                "Assad the Father did not kill tens of thousands of his own citizens in one week in El Hamma in Syria because of Israel .\n" +
                "The Taliban control of Afghanistan and the civil war there had nothing to do with Israel .\n" +
                "The Libyan blowing up of the Pan - Am flight had nothing to do with Israel , and I could go on and on and on .\n" +
                "The root of the trouble is that this entire Moslem region is totally dysfunctional , by any standard of the word , and would have been so even if Israel had joined the Arab league and an independent Palestine had existed for 100 years .\n" +
                "The 22 member countries of the Arab league , from Mauritania to the Gulf States , have a total population of 300 millions , larger than the US and almost as large as the EU before its expansion .\n" +
                "They have a land area larger than either the US or all of Europe .\n" +
                "These 22 countries , with all their oil and natural resources , have a combined GDP smaller than that of Netherlands plus Belgium and equal to half of the GDP of California alone .\n" +
                "Within this meager GDP , the gaps between rich and poor are beyond belief .\n" +
                "and too many of the rich made their money not by succeeding in business , but by being corrupt rulers .\n" +
                "The social status of women is far below what it was in the Western World 150 years ago .\n" +
                "Human rights are below any reasonable standard , in spite of the grotesque fact that Libya was elected Chair of the UN Human Rights commission .\n" +
                "According to a report prepared by a committee of Arab intellectuals and published under the auspices of the U.N. , the number of books translated by the entire Arab world is much smaller than what little Greece alone translates .\n" +
                "The total number of scientific publications of 300 million Arabs is less than that of 6 million Israelis .\n" +
                "Birth rates in the region are very high , increasing the poverty , the social gaps and the cultural decline .\n" +
                "And all of this is happening in a region , which only 30 years ago , was believed to be the next wealthy part of the world , and in a Moslem area , which developed , at some point in history , one of the most advanced cultures in the world .\n" +
                "It is fair to say that this creates an unprecedented breeding ground for cruel dictators , terror networks , fanaticism , incitement , suicide murders and general decline .\n" +
                "It is also a fact that almost everybody in the region blames this situation on the United States , on Israel , on Western Civilization , on Judaism and Christianity , on anyone and anything , except themselves .\n" +
                "A word about the millions of decent , honest , good people who are either devout Moslems or are not very religious but grew up in Moslem families .\n" +
                "They are double victims of an outside world , which now develops Islamophobia , and of their own environment which breaks their heart by being totally dysfunctional .\n" +
                "The problem is that the vast silent majority of these Moslems are not part of the terror and the incitement , but they also do not stand up political leaders , intellectuals , business people and many others .\n" +
                "Many of them can certainly tell right from wrong , but are afraid to express their views .\n" +
                "The events of the last few years have amplified four issues , which have always existed , but have never been as rampant as in the present upheaval in the region .\n" +
                "A few more years may pass before everybody acknowledges that it is a World War .\n" +
                "but we are already well into it .\n" +
                "These are the four main pillars of the current World Conflict , or perhaps we should already refer to it as the undeclared World War III : against it They become accomplices , by omission , and this applies to .\n" +
                "*1 .\n" +
                "Suicide murders are not a new invention .\n" +
                "but they have been made popular , if I may use this expression , only lately .\n" +
                "Even after September 11 , it seems that most of the Western World does not yet understand this weapon .\n" +
                "It is a very potent psychological weapon .\n" +
                "Its real direct impact is relatively minor .\n" +
                "The total number of casualties from hundreds of suicide murders within Israel in the last three years is much smaller than those due to car accidents .\n" +
                "September 11 was quantitatively much less lethal than many earthquakes .\n" +
                "More people die from AIDS in one day in Africa than all the Russians who died in the hands of Chechnya - based Moslem suicide murderers since that conflict started .\n" +
                "Saddam killed every month more people than all those who died from suicide murders since the Coalition occupation of Iraq .\n" +
                "So what is all the fuss about suicide killings ?\n" +
                "It creates headlines .\n" +
                "It is spectacular .\n" +
                "It is frightening .\n" +
                "It is a very cruel death with bodies dismembered and horrible severe lifelong injuries to many of the wounded .\n" +
                "It is always shown on television in great detail .\n" +
                "One such murder , with the help of hysterical media coverage , can destroy the tourism industry of a country for quite a while , as it did in Bali and in Turkey .\n" +
                "But the real fear comes from the undisputed fact that no defense and no preventive measures can succeed against a determined suicide murderer .\n" +
                "This has not yet penetrated the thinking of the Western World .\n" +
                "The U.S. and Europe are constantly improving their defense against the last murder , not the next one .\n" +
                "We may arrange for the best airport security in the world .\n" +
                "But if you want to murder by suicide , you do not have to board a plane in order to explode yourself and kill many people .\n" +
                "Who could stop a suicide murder in the midst of the crowded line waiting to be checked by the airport metal detector ?\n" +
                "How about the lines to the check - in counters in a busy travel period ?\n" +
                "Put a metal detector in front of every train station in Spain and the terrorists will get the buses .\n" +
                "Protect the buses and they will explode in movie theaters , concert halls , supermarkets , shopping malls , schools and hospitals .\n" +
                "Put guards in front of every concert hall and there will always be a line of people to be checked by the guards .\n" +
                "and this line will be the target , not to speak of killing the guards themselves .\n" +
                "You can somewhat reduce your vulnerability by preventive and defensive measures and by strict border controls but not eliminate it and definitely not win the war in a defensive way .\n" +
                "And it is a war .\n" +
                "What is behind the suicide murders ?\n" +
                "Money is , money and power and cold - blooded murderous incitement , nothing else .\n" +
                "It has nothing to do with true fanatic religious beliefs .\n" +
                "No Moslem preacher has ever blown himself up .\n" +
                "No son of an Arab politician or religious leader has ever blown himself up .\n" +
                "No relative of anyone influential has done it .\n" +
                "Would n't you expect some of the religious leaders to do it themselves , or to talk their sons into doing it , if this is truly a supreme act of religious fervor ?\n" +
                "Are n't they interested in the benefits of going to Heaven ?\n" +
                "Instead , they send outcast women , naive children , retarded people and young incited hotheads .\n" +
                "They promise them the delights , mostly sexual , of the next world , and pay their families handsomely after the supreme act is performed and enough innocent people are dead .\n" +
                "Suicide murders also have nothing to do with poverty and despair .\n" +
                "The poorest region in the world , by far , is Africa .\n" +
                "It never happens there .\n" +
                "There are numerous desperate people in the world , in different cultures , countries and continents .\n" +
                "Desperation does not provide anyone with explosives , reconnaissance and transportation .\n" +
                "There was certainly more despair in Saddam's Iraq than in Paul Bremmer's Iraq .\n" +
                "and no one exploded himself .\n" +
                "A suicide murder is simply a horrible , vicious weapon of cruel , inhuman , cynical , well - funded terrorists , with no regard to human life , including the life of their fellow countrymen , but with very high regard to their own affluent well - being and their hunger for power .\n" +
                "The only way to fight this new popular weapon is identical to the only way in which you fight organized crime or pirates on the high seas : the offensive way .\n" +
                "Like in the case of organized crime , it is crucial that the forces on the offensive be united and it is crucial to reach the top of the crime pyramid .\n" +
                "You can not eliminate organized crime by arresting the little drug dealer on the street corner .\n" +
                "You must go after the head of the Family .\n" +
                "If part of the public supports it , others tolerate it , many are afraid of it and some try to explain it away by poverty or by a miserable childhood , organized crime will thrive and so will terrorism .\n" +
                "The United States understands this now , after September 11 .\n" +
                "Russia is beginning to understand it .\n" +
                "Turkey understands it well .\n" +
                "I am very much afraid that most of Europe still does not understand it .\n" +
                "Unfortunately , it seems that Europe will understand it only after suicide murders arrive in Europe in a big way .\n" +
                "In my humble opinion , this will definitely happen .\n" +
                "The Spanish trains and the Istanbul bombings are only the beginning .\n" +
                "The unity of the Civilized World in fighting this horror is absolutely indispensable .\n" +
                "Until Europe wakes up , this unity will not be achieved .\n" +
                "*2 .\n" +
                "Words can be lethal .\n" +
                "They kill people .\n" +
                "It is often said that politicians , diplomats and perhaps also lawyers and business people must sometimes lie , as part of their professional life .\n" +
                "But the norms of politics and diplomacy are childish , in comparison with the level of incitement and total absolute deliberate fabrications , which have reached new heights in the region we are talking about .\n" +
                "An incredible number of people in the Arab world believe that September 11 never happened , or was an American provocation or , even better , a Jewish plot .\n" +
                "You all remember the Iraqi Minister of Information , Mr. Mouhamad Said al-Sahaf and his press conferences when the US forces were already inside Baghdad .\n" +
                "Disinformation at time of war is an accepted tactic .\n" +
                "But to stand , day after day , and to make such preposterous statements , known to everybody to be lies , without even being ridiculed in your own milieu , can only happen in this region .\n" +
                "Mr. Sahaf eventually became a popular icon as a court jester .\n" +
                "but this did not stop some allegedly respectable newspapers from giving him equal time .\n" +
                "It also does not prevent the Western press from giving credence , every day , even now , to similar liars .\n" +
                "After all , if you want to be an anti-Semite , there are subtle ways of doing it .\n" +
                "You do not have to claim that the holocaust never happened , and that the Jewish temple in Jerusalem never existed .\n" +
                "But millions of Moslems are told by their leaders that this is the case .\n" +
                "When these same leaders make other statements , the Western media report them as if they could be true .\n" +
                "It is a daily occurrence that the same people who finance , arm and dispatch suicide murderers , condemn the act in English in front of western TV cameras , talking to a world audience , which even partly believes them .\n" +
                "It is a daily routine to hear the same leader making opposite statements in Arabic to his people and in English to the rest of the world .\n" +
                "Incitement by Arab TV , accompanied by horror pictures of mutilated bodies , has become a powerful weapon of those who lie , distort and want to destroy everything .\n" +
                "Little children are raised on deep hatred and on admiration of so - called martyrs .\n" +
                "and the Western World does not notice it because its own TV sets are mostly tuned to soap operas and game shows .\n" +
                "I recommend to you , even though most of you do not understand Arabic , to watch Al Jazeera , from time to time .\n" +
                "You will not believe your own eyes .\n" +
                "But words also work in other ways , more subtle .\n" +
                "A demonstration in Berlin , carrying banners supporting Saddam's regime and featuring three - year old babies dressed as suicide murderers , is defined by the press and by political leaders as a peace demonstration .\n" +
                "You may support or oppose the Iraq war , but to refer to fans of Saddam , Arafat or Bin Laden as peace activists is a bit too much .\n" +
                "A woman walks into an Israeli restaurant in mid-day , eats , observes families with old people and children eating their lunch in the adjacent tables and pays the bill .\n" +
                "She then blows herself up , killing 20 people , including many children , with heads and arms rolling around in the restaurant .\n" +
                "She is called martyr by several Arab leaders and activist by the European press .\n" +
                "Dignitaries condemn the act but visit her bereaved family and the money flows .\n" +
                "There is a new game in town .\n" +
                "The actual murderer is called the military wing .\n" +
                "the one who pays him , equips him and sends him is now called the political wing .\n" +
                "and the head of the operation is called the spiritual leader .\n" +
                "There are numerous other examples of such Orwellian nomenclature , used every day not only by terror chiefs but also by Western media .\n" +
                "These words are much more dangerous than many people realize .\n" +
                "They provide an emotional infrastructure for atrocities .\n" +
                "It was Joseph Goebbels who said that if you repeat a lie often enough , people will believe it .\n" +
                "He is now being outperformed by his successors .\n" +
                "*3 .\n" +
                "Huge amounts of money , which could have solved many social problems in this dysfunctional part of the world , are channeled into three concentric spheres supporting death and murder .\n" +
                "In the inner circle are the terrorists themselves .\n" +
                "The money funds their travel , explosives , hideouts and permanent search for soft vulnerable targets .\n" +
                "The inner circles are primarily financed by terrorist states like Iran and Syria , until recently also by Iraq and Libya and earlier also by some of the Communist regimes .\n" +
                "These states , as well as the Palestinian Authority , are the safe havens of the wholesale murder vendors .\n" +
                "They are surrounded by a second wider circle of direct supporters , planners , commanders , preachers , all of whom make a living , usually a very comfortable living , by serving as terror infrastructure .\n" +
                "Finally , we find the third circle of so - called religious , educational and welfare organizations , which actually do some good , feed the hungry and provide some schooling , but brainwash a new generation with hatred , lies and ignorance .\n" +
                "This circle operates mostly through mosques , madrasas and other religious establishments but also through inciting electronic and printed media .\n" +
                "It is this circle that makes sure that women remain inferior , that democracy is unthinkable and that exposure to the outside world is minimal .\n" +
                "It is also that circle that leads the way in blaming everybody outside the Moslem world , for the miseries of the region .\n" +
                "The outer circle is largely financed by Saudi Arabia , but also by donations from certain Moslem communities in the United States and Europe and , to a smaller extent , by donations of European Governments to various NGO's and by certain United Nations organizations , whose goals may be noble , but they are infested and exploited by agents of the outer circle .\n" +
                "The Saudi regime , of course , will be the next victim of major terror , when the inner circle will explode into the outer circle .\n" +
                "The Saudis are beginning to understand it , but they fight the inner circles , while still financing the infrastructure at the outer circle .\n" +
                "Figuratively speaking , this outer circle is the guardian , which makes sure that the people look and listen inwards to the inner circle of terror and incitement , rather than to the world outside .\n" +
                "Some parts of this same outer circle actually operate as a result of fear from , or blackmail by , the inner circles .\n" +
                "The horrifying added factor is the high birth rate .\n" +
                "Half of the population of the Arab world is under the age of 20 , the most receptive age to incitement , guaranteeing two more generations of blind hatred .\n" +
                "Some of the leaders of these various circles live very comfortably on their loot .\n" +
                "You meet their children in the best private schools in Europe , not in the training camps of suicide murderers .\n" +
                "The Jihad soldiers join packaged death tours to Iraq and other hotspots , while some of their leaders ski in Switzerland .\n" +
                "Mrs. Arafat , who lives in Paris with her daughter , receives tens of thousands of dollars per month from the allegedly bankrupt Palestinian Authority , while a typical local ringleader of the Al-Aksa brigade , reporting to Arafat , receives only a cash payment of a couple of hundred dollars , for performing murders at the retail level .\n" +
                "*4 .\n" +
                "The civilized world believes in democracy , the rule of law , including international law , human rights , free speech and free press , among other liberties .\n" +
                "There are naive old - fashioned habits such as respecting religious sites and symbols , not using ambulances and hospitals for acts of war , avoiding the mutilation of dead bodies and not using children as human shields or human bombs .\n" +
                "Never in history , not even in the Nazi period , was there such total disregard of all of the above as we observe now .\n" +
                "Every student of political science debates how you prevent an anti-democratic force from winning a democratic election and abolishing democracy .\n" +
                "Other aspects of a civilized society must also have limitations .\n" +
                "Can a policeman open fire on someone trying to kill him ?\n" +
                "Can a government listen to phone conversations of terrorists and drug dealers ?\n" +
                "Does free speech protect you when you shout fire in a crowded theater ?\n" +
                "Should there be death penalty , for deliberate multiple murders ?\n" +
                "These are the old - fashioned dilemmas .\n" +
                "But now we have an entire new set .\n" +
                "Do you raid a mosque , which serves as a terrorist ammunition storage ?\n" +
                "Do you return fire , if you are attacked from a hospital ?\n" +
                "Do you storm a church taken over by terrorists who took the priests hostages ?\n" +
                "Do you search every ambulance after a few suicide murderers use ambulances to reach their targets ?\n" +
                "Do you strip every woman because one pretended to be pregnant and carried a suicide bomb on her belly ?\n" +
                "Do you shoot back at someone trying to kill you , standing deliberately behind a group of children ?\n" +
                "Do you raid terrorist headquarters , hidden in a mental hospital ?\n" +
                "Do you shoot an arch-murderer who deliberately moves from one location to another , always surrounded by children ?\n" +
                "All of these happen daily in Iraq and in the Palestinian areas .\n" +
                "What do you do ?\n" +
                "Well , you do not want to face the dilemma .\n" +
                "But it can not be avoided .\n" +
                "Suppose , for the sake of discussion , that someone would openly stay in a well - known address in Teheran , hosted by the Iranian Government and financed by it , executing one atrocity after another in Spain or in France , killing hundreds of innocent people , accepting responsibility for the crimes , promising in public TV interviews to do more of the same , while the Government of Iran issues public condemnations of his acts but continues to host him , invite him to official functions and treat him as a great dignitary .\n" +
                "I leave it to you as homework to figur out what Spain or France would have done , in such a situation .\n" +
                "The problem is that the civilized world is still having illusions about the rule of law in a totally lawless environment .\n" +
                "It is trying to play ice hockey by sending a ballerina ice - skater into the ring or to knock out a heavyweight boxer by a chess player .\n" +
                "In the same way that no country has a law against cannibals eating its prime minister , because such an act is unthinkable , international law does not address killers shooting from hospitals , mosques and ambulances , while being protected by their Government or society .\n" +
                "International law does not know how to handle someone who sends children to throw stones , stands behind them and shoots with immunity and can not be arrested because he is sheltered by a Government .\n" +
                "International law does not know how to deal with a leader of murderers who is royally and comfortably hosted by a country , which pretends to condemn his acts or just claims to be too weak to arrest him .\n" +
                "The amazing thing is that all of these crooks demand protection under international law , and define all those who attack them as war criminals , with some Western media repeating the allegations .\n" +
                "The good news is that all of this is temporary .\n" +
                "because the evolution of international law has always adapted itself to reality .\n" +
                "The punishment for suicide murder should be death or arrest before the murder , not during and not after .\n" +
                "After every world war , the rules of international law have changed , and the same will happen after the present one .\n" +
                "But during the twilight zone , a lot of harm can be done .\n" +
                "The picture I described here is not pretty .\n" +
                "What can we do about it ?\n" +
                "In the short run , only fight and win .\n" +
                "In the long run , only educate the next generation and open it to the world .\n" +
                "The inner circles can and must be destroyed by force .\n" +
                "The outer circle can not be eliminated by force .\n" +
                "Here we need financial starvation of the organizing elite , more power to women , more education , counter-propaganda , boycott whenever feasible and access to Western media , internet and the international scene .\n" +
                "Above all , we need a total absolute unity and determination of the civilized world against all three circles of evil .\n" +
                "Allow me , for a moment , to depart from my alleged role as a taxi driver and return to science .\n" +
                "When you have a malignant tumor , you may remove the tumor itself surgically .\n" +
                "You may also starve it by preventing new blood from reaching it from other parts of the body , thereby preventing new supplies from expanding the tumor .\n" +
                "If you want to be sure , it is best to do both .\n" +
                "But before you fight and win , by force or otherwise , you have to realize that you are in a war , and this may take Europe a few more years .\n" +
                "In order to win , it is necessary to first eliminate the terrorist regimes , so that no Government in the world will serve as a safe haven for these people .\n" +
                "I do not want to comment here on whether the American - led attack on Iraq was justified from the point of view of weapons of mass destruction or any other pre-war argument , but I can look at the post-war map of Western Asia .\n" +
                "Now that Afghanistan , Iraq and Libya are out , two and a half terrorist states remain : Iran , Syria and Lebanon , the latter being a Syrian colony .\n" +
                "Perhaps Sudan should be added to the list .\n" +
                "As a result of the conquest of Afghanistan and Iraq , both Iran and Syria are now totally surrounded by territories unfriendly to them .\n" +
                "Iran is encircled by Afghanistan , by the Gulf States , Iraq and the Moslem republics of the former Soviet Union .\n" +
                "Syria is surrounded by Turkey , Iraq , Jordan and Israel .\n" +
                "This is a significant strategic change and it applies strong pressure on the terrorist countries .\n" +
                "It is not surprising that Iran is so active in trying to incite a Shiite uprising in Iraq .\n" +
                "I do not know if the American plan was actually to encircle both Iran and Syria , but that is the resulting situation .\n" +
                "In my humble opinion , the number one danger to the world today is Iran and its regime .\n" +
                "It definitely has ambitions to rule vast areas and to expand in all directions .\n" +
                "It has an ideology which claims supremacy over Western culture .\n" +
                "It is ruthless .\n" +
                "It has proven that it can execute elaborate terrorist acts without leaving too many traces , using Iranian Embassies .\n" +
                "It is clearly trying to develop nuclear weapons .\n" +
                "Its so - called moderates and conservatives play their own virtuoso version of the good - cop versus bad - cop game .\n" +
                "Iran sponsors Syrian terrorism .\n" +
                "it is certainly behind much of the action in Iraq .\n" +
                "it is fully funding the Hezbollah and , through it , the Palestinian Hamas and Islamic Jihad .\n" +
                "it performed acts of terror at least in Europe and in South America and probably also in Uzbekistan and Saudi Arabia .\n" +
                "and it truly leads a multi-national terror consortium , which includes , as minor players , Syria , Lebanon and certain Shiite elements in Iraq .\n" +
                "Nevertheless , most European countries still trade with Iran , try to appease it and refuse to read the clear signals .\n" +
                "In order to win the war it is also necessary to dry the financial resources of the terror conglomerate .\n" +
                "It is pointless to try to understand the subtle differences between the Sunni terror of Al Qaeda and Hamas and the Shiite terror of Hezbollah , Sadr and other Iranian - inspired enterprises .\n" +
                "When it serves their business needs , all of them collaborate beautifully .\n" +
                "It is crucial to stop Saudi and other financial support of the outer circle , which is the fertile breeding ground of terror .\n" +
                "It is important to monitor all donations from the Western World to Islamic organizations , to monitor the finances of international relief organizations and to react with forceful economic measures to any small sign of financial aid to any of the three circles of terrorism .\n" +
                "It is also important to act decisively against the campaign of lies and fabrications and to monitor those Western media who collaborate with it out of naivety , financial interests or ignorance .\n" +
                "Above all , never surrender to terror .\n" +
                "No one will ever know whether the recent elections in Spain would have yielded a different result , if not for the train bombings a few days earlier .\n" +
                "But it really does not matter .\n" +
                "What matters is that the terrorists believe that they caused the result and that they won by driving Spain out of Iraq .\n" +
                "The Spanish story will surely end up being extremely costly to other European countries , including France , who is now expelling inciting preachers and forbidding veils and including others who sent troops to Iraq .\n" +
                "In the long run , Spain itself will pay even more .\n" +
                "Is the solution a democratic Arab world ?\n" +
                "If by democracy we mean free elections but also free press , free speech , a functioning judicial system , civil liberties , equality to women , free international travel , exposure to international media and ideas , laws against racial incitement and against defamation , and avoidance of lawless behavior regarding hospitals , places of worship and children , then yes , democracy is the solution .\n" +
                "If democracy is just free elections , it is likely that the most fanatic regime will be elected , the one whose incitement and fabrications are the most inflammatory .\n" +
                "We have seen it already in Algeria and , to a certain extent , in Turkey .\n" +
                "It will happen again , if the ground is not prepared very carefully .\n" +
                "On the other hand , a certain transition democracy , as in Jordan , may be a better temporary solution , paving the way for the real thing , perhaps in the same way that an immediate sudden democracy did not work in Russia and would not have worked in China .\n" +
                "I have no doubt that the civilized world will prevail .\n" +
                "But the longer it takes us to understand the new landscape of this war , the more costly and painful the victory will be .\n" +
                "Its understandable recoil from wars , following the horrors of World War II , may cost thousands of additional innocent lives , before the tide will turn .");

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
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