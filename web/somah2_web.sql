-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 02 Décembre 2016 à 21:35
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `somah2_web`
--
CREATE DATABASE IF NOT EXISTS `somah2_web` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `somah2_web`;

-- --------------------------------------------------------

--
-- Structure de la table `association_period_topic`
--

DROP TABLE IF EXISTS `association_period_topic`;
CREATE TABLE IF NOT EXISTS `association_period_topic` (
  `period_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`period_id`,`topic_id`),
  KEY `association_period_topic_ibfk_2` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `association_period_topic`
--

TRUNCATE TABLE `association_period_topic`;
--
-- Contenu de la table `association_period_topic`
--

INSERT INTO `association_period_topic` (`period_id`, `topic_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9);

-- --------------------------------------------------------

--
-- Structure de la table `content`
--

DROP TABLE IF EXISTS `content`;
CREATE TABLE IF NOT EXISTS `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` longblob,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `content_ibfk_1` (`topic_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Vider la table avant d'insérer `content`
--

TRUNCATE TABLE `content`;
--
-- Contenu de la table `content`
--

INSERT INTO `content` (`id`, `image`, `topic_id`) VALUES
(1, NULL, 1),
(2, NULL, 1),
(3, NULL, 2),
(4, NULL, 2),
(5, NULL, 2),
(6, NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `content_tr`
--

DROP TABLE IF EXISTS `content_tr`;
CREATE TABLE IF NOT EXISTS `content_tr` (
  `content_id` int(11) NOT NULL,
  `language_code` varchar(2) NOT NULL,
  `title` text NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`content_id`,`language_code`),
  KEY `content_tr_ibfk_2` (`language_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `content_tr`
--

TRUNCATE TABLE `content_tr`;
--
-- Contenu de la table `content_tr`
--

INSERT INTO `content_tr` (`content_id`, `language_code`, `title`, `text`) VALUES
(1, 'en', 'Benefits for the baby', 'Breast milk provides the ideal nutrition for infants. It has a nearly perfect mix of vitamins, protein, and fat -- everything your baby needs to grow. And it''s all provided in a form more easily digested than infant formula. Breast milk contains antibodies that help your baby fight off viruses and bacteria. Plus, babies who are breastfed exclusively for the first 4-6 months, without any formula, have fewer ear infections, respiratory illnesses, and bouts of diarrhea. They also have fewer hospitalizations and trips to the doctor.\r\n\r\nBreastfeeding has been linked to higher IQ scores in later childhood in some studies. What''s more, the physical closeness, skin-to-skin touching, and eye contact all help your baby bond with you and feel secure. Breastfed infants are more likely to gain the right amount of weight as they grow rather than become overweight children. The Directorate of Health says breastfeeding also may play a role in the prevention of SIDS (sudden infant death syndrome). It''s been thought to lower the risk of diabetes, obesity, and certain cancers as well, but more research is needed.'),
(1, 'nb','Fordeler med amming', 'Morsmelk gir den ideelle ernæring for spedbarn. Den har en nesten perfekt blanding av vitaminer, proteiner og fett - alt barnet trenger for å vokse. Og det er alt i en form lettere fordøyd enn morsmelkerstatning. Morsmelk inneholder antistoffer som hjelper barnet ditt å bekjempe virus og bakterier. Plus, babyer som blir ammet eksklusivt for de første 4-6 månedene, uten noen formel, har færre ørebetennelser, luftveissykdommer, og utbrudd av diaré. De har også færre sykehusinnleggelser og turer til legen. Amming har vært knyttet til høyere IQ i senere barndom i noen studier. Hva mer, den fysiske nærhet, hud-til-hud rørende, og øyekontakt alle hjelpe babyen din obligasjon med deg og føle seg trygge. Ammede spedbarn er mer sannsynlig å få riktig mengde vekt som de vokser i stedet blir overvektige barn. Helsedirektoratet sier amming kan også spille en rolle i forebygging av krybbedød (krybbedød). Det er blitt antatt å redusere risikoen for diabetes, fedme, og visse kreftformer også, men det trengs mer forskning.'),
(1, 'ar', 'فوائد للطفل', 'يوفر حليب الثدي التغذية المثالية للأطفال الرضع. انها مزيج ما يقرب من الكمال من الفيتامينات والبروتينات، والدهون - كل شيء طفلك يحتاج إلى النمو. وانه هو تزويد جميع في شكل هضمها بسهولة أكبر من حليب الأطفال. يحتوي حليب الأم على أجسام مضادة تساعد الطفل على محاربة الفيروسات والبكتيريا. بالاضافة الى ذلك، الأطفال الذين يرضعون رضاعة طبيعية خالصة للأشهر 4-6 الأولى، دون أي صيغة، لديها التهابات الأذن أقل، أمراض الجهاز التنفسي، ونوبات من الإسهال. كما أن لديها عدد أقل من المستشفيات ورحلات إلى الطبيب. \ ارتبط ص \ ن \ ص \ ن الرضاعة الطبيعية لدرجات معدل الذكاء العالي في مرحلة الطفولة في وقت لاحق في بعض الدراسات. ما هو أكثر من ذلك، ولمس التقارب الجسدي، والجلد الى الجلد، واتصال العين كل تساعد السندات طفلك معك ويشعر بالأمان. هم أكثر عرضة لاكتساب الحق في مبلغ من الوزن لأنها تنمو بدلا من الأطفال يعانون من زيادة الوزن تصبح الرضع. وتقول مديرية الصحة الرضاعة الطبيعية أيضا قد تلعب دورا في الوقاية من الدول الجزرية الصغيرة النامية (الرضع المفاجئ متلازمة الموت). وكان يعتقد أن خفض خطر الإصابة بمرض السكري، والسمنة، وبعض أنواع السرطان كذلك، ولكن هناك حاجة إلى مزيد من الأبحاث.'),
(1, 'pl', 'Korzyści z karmienia piersią','Mleko matki jest idealnym żywienia dla niemowląt. Ma prawie doskonałą mieszankę witamin, białka i tłuszczu - wszystko twoje dziecko potrzebuje do wzrostu. A wszystko podane w formie łatwiej trawione niż preparaty dla niemowląt. Mleko matki zawiera przeciwciała, które pomagają dziecku zwalczyć wirusy i bakterie. Plus, dzieci, które są karmione wyłącznie piersią przez pierwsze 4-6 miesięcy, bez żadnego wzoru, mają mniej infekcji ucha, choroby układu oddechowego, a ataki biegunki. Mają też mniej hospitalizacji i wyjazdy do lekarza.Karmienie piersią jest związana z wyższym IQ w późniejszym dzieciństwie, w niektórych badaniach. Co więcej, fizyczna bliskość, skóra do skóry kontaktu wzrokowego wzruszającą i wszystkim pomóc więź dziecka ze sobą i czuć się bezpiecznie. Niemowlęta karmione piersią są bardziej prawdopodobne, aby uzyskać odpowiednią ilość masy, jak rosną, a nie stają się dzieci z nadwagą. Dyrekcja Zdrowia mówi piersią może również odgrywać rolę w zapobieganiu SIDS (zespół nagłej śmierci niemowląt). To uważano, aby obniżyć ryzyko cukrzycy, otyłości i niektórych nowotworów, a także, ale potrzebne są dalsze badania.'),
(1, 'ur', 'بچے کے لئے فوائد', 'چھاتی کے دودھ بچوں کے لئے مثالی غذائیت فراہم کرتا ہے. آپ کے بچے کے بڑھنے کی ضرورت ہے سب کچھ ہے - یہ وٹامن، پروٹین، اور چربی کا ایک تقریبا کامل مرکب ہے. اور یہ سب زیادہ آسانی بچے فارمولہ مقابلے پچا ایک شکل میں فراہم کی ہے. چھاتی کے دودھ اپنے بچے کو وائرس اور بیکٹیریا سے لڑنے میں مدد دیتا مائپنڈوں مشتمل. پلس، بچے جو کسی بھی فارمولے کے بغیر، سب سے پہلے 4-6 ماہ کے لئے خصوصی طور پر دودھ پیا رہے ہیں، اس سے کم کان میں انفیکشن، نظام تنفس کی بیماریوں، اور اسہال کے ادوار ہے. انہوں نے یہ بھی قدرے کم ہسپتال اور ڈاکٹر کے دوروں ہے. رضاعت کچھ مطالعہ میں بعد میں بچپن میں زیادہ سکور سے جوڑا گیا ہے. کیا زیادہ ہے، جسمانی قربت، جلد کے جلد سے چھونے، اور آنکھ سے رابطہ کریں سب آپ کے ساتھ آپ کے بچے بانڈ کی مدد اور محفوظ محسوس. دودھ پیا شیرخوار وہ بن زیادہ وزن والے بچوں کی بجائے بڑھنے کے طور پر وزن کے دائیں رقم حاصل کرنے کے امکانات زیادہ ہوتے ہیں. صحت کی ڈائریکٹوریٹ دودھ پلانے کے علاوہ میں نے ایس (اچانک بچے کی موت سنڈروم) ð ے کی روک تھام میں ایک کردار ادا کر سکتے کہنا ہے کہ. اس کے ساتھ ساتھ ذیابیطس، موٹاپا، اور بعض کینسر کے خطرے کو کم کرنے کے لئے سوچا گیا ہے، لیکن زیادہ تحقیق کی ضرورت ہے.'),
(1, 'so', 'Faa iidooyinka naas nuujinta','Caanaha naaska waxay bixisaa nafaqada ugu fiican ee ilmaha yaryar. Waxay leedahay isku jira ku dhawaad \u200B\u200Bqumman yahay fiitamiinada, protein, iyo xaydha - wax kasta oo ilmahaagu u baahan yahay si uu u koro. Oo waxaa oo dhan bixinayo foom si fudud ku milmaan badan caanaha budada. Caanaha naaska waxaa ku jira unugyada oo kaa caawin ilmahaaga ay iskaga caabiyaan fayraska iyo bakteeriyada. Plus, ilmaha ayaa caanaha naaska ee 4-6 bilood ee hore, iyada oo aan caanaha kasta, waxay leeyihiin cudurada yar dhegta, cudurrada neef mareenka, oo joog- shuban. Sidoo kale waxay leeyihiin isbitaal yar iyo safarada dhakhtarka.Naas-nuujinta ayaa lala xiriirinayay in ay dhibcaha IQ sare ee carruurnimada dambe ee daraasadaha qaarkood. Waa maxay more, soke ee jirka, maqaarka-to-maqaarka taabashada, iyo indhaha oo dhan caawin debaajigaaga ilmahaaga adiga iyo dareemaan amaan. dhallaanka naaska u badan tahay in ay helaan lacagta saxda ah oo miisaankeedu yahay sidii ay halkii koraan badan carruurta xad dhaaf noqday. Agaasinka Caafimaadka ayaa sheegay in naas-nuujinta sidoo kale ka ciyaari karaa doorka ah ee ka hortagga SIDS (Infant Death Syndrome). Waxaa loo maleynayay in ay hoos u halista ah ee macaanka, cayilka, iyo kansarada qaar iyo sidoo, laakiin waxaa loo baahan yahay cilmi-baaris dheeraad ah.'),
(1, 'lt', 'Privalumai žindymo','Motinos pienas yra idealus mitybos kūdikiams. Ji beveik tobulas derinys vitaminų, baltymų, ir riebalų - viskas jūsų kūdikis turi augti. Ir visa tai pateikiama tokia forma daugiau lengvai virškinamas nei kūdikių formulę. Motinos pienas yra antikūnų, kurie padeda jūsų kūdikis atsikratyti virusų ir bakterijų. Be to, kūdikiai, kurie maitinami tik motinos pienu per pirmuosius 4-6 mėnesius, be jokių formulę, turi mažiau ausų infekcijas, kvėpavimo takų ligas, ir seansų viduriavimas. Jie taip pat turi mažiau hospitalizacijos ir keliones į gydytoją. Žindymas buvo susijęs su aukštojo IQ balai vėliau vaikystės kai kurių tyrimų. Kas daugiau, fizinis artumas, odos prie-odos neliesti, ir akis visa tai padės jūsų kūdikio ryšį su jumis ir jaustis saugus. Žindomiems kūdikiams, yra labiau linkę įgyti reikiamą kiekį svorio, kaip jie auga, o ne tapti antsvorio vaikams. Sveikatos direktoratas sako žindymas taip pat gali vaidinti vaidmenį morgą (staigus kūdikių mirties sindromas) prevencijai. Tai buvo manoma, kad sumažinti diabeto, nutukimo, ir tam tikrų vėžio formų riziką, taip pat, bet reikia daugiau mokslinių tyrimų.'),
(2, 'en','Preparation for breastfeeding ','The most important preparation is that you know yourself that you want to breastfeed. It can be difficult starting, but there is advice for the most part. '),
(2, 'nb', 'Forberedelse til amming','Den viktigste forberedelsen er at du kjenner deg selv som du ønsker å amme. Det kan være vanskelig start, men det er råd for det meste.'),
(2, 'ar', 'استعدادا للرضاعة', ' إعداد الأكثر أهمية هو أن تعرف نفسك أنك تريد أن الرضاعة الطبيعية. ويمكن أن يكون بداية صعبة، ولكن هناك المشورة بالنسبة للجزء الأكبر. '),
(2,'pl', 'Przygotowanie do karmienia piersią','Najważniejszy jest fakt, że preparat sama wiesz, że chcesz karmić piersią. To może być trudne wyjścia, ale jest rada dla większości.'),
(2,'ur', 'دودھ پلانے کے لئے تیاری ', 'سب سے اہم تیاری تم اپنے آپ کو معلوم ہے کہ تم کو دودھ پلانا چاہتے ہیں. یہ مشکل اغاز ہو سکتا ہے، لیکن سب سے زیادہ حصہ کے لئے مشورہ ہے.'),
(2,'so', 'Diyaarinta waayo naasnuujinta','The diyaarinta ugu muhiimsan waa in aad naftaada og nahay in aad rabto in aad naaska. Waxay noqon kartaa laga bilaabo adag, laakiin waxaa jira talo qaybta ugu.'),
(2,'lt', 'Pasirengimas žindymo', 'Svarbiausia preparatas yra, kad jūs žinote, sau, kad jūs norite žindyti. Jis gali būti sunku išeities, bet yra konsultacijos didžiąja dalimi.'),
(3, 'en', 'Preparation for breastfeeding ','If you have not breastfed before, you may have questions about certain things, such as whether the breasts or breast bud the shape does not matter, whether you can breastfeed despite breast operations, the ability to breastfeed can be inherited or what you can or should eat and drink. On most issues, there are answers.'),
(3, 'nb', 'Forberedelse til amming','Hvis du ikke har ammet før, kan du har spørsmål om visse ting, for eksempel om brystene eller bryst knopp form spiller ingen rolle, om du kan amme tross brystoperasjoner, evnen til å amme kan være arvelig eller hva du kan eller bør spise og drikke. På de fleste saker, det er svar.'),
(3, 'ar', 'استعدادا للرضاعة', 'إذا لم تكن الرضاعة الطبيعية قبل، قد يكون لديك تساؤلات حول بعض الأمور، مثل ما إذا كان الثدي أو الثدي برعم الشكل لا يهم، ما إذا كان يمكنك إرضاع رغم عمليات الثدي، والقدرة على الرضاعة الطبيعية يمكن أن تكون وراثية أو ما يمكن أو ينبغي أكل و اشرب. في معظم القضايا، هناك إجابات.'),
(3, 'pl', 'Przygotowanie do karmienia piersią','Jeśli nie karmione piersią przed, może masz pytania dotyczące pewnych rzeczy, takich jak: czy piersi lub piersi pączek kształt nie ma znaczenia, czy można karmić piersią pomimo operacji piersi, zdolność do karmienia piersią mogą być dziedziczone lub co może lub powinna jeść i pić. W większości spraw, istnieją odpowiedzi.'),
(3, 'ur', 'دودھ پلانے کے لئے تیاری', 'تم سے پہلے دودھ پیا نہیں ہوا ہے تو، آپ اس طرح کے سینوں یا چھاتی بڈ چاہے شکل کوئی فرق نہیں ہے، آپ کو چھاتی کی کارروائیوں کے باوجود دودھ پلانا کر سکتا ہے یا، دودھ پلانے کے لئے کی صلاحیت کو وراثت میں ملا جا سکتا ہے یا کیا آپ کر سکتے یا کرنا چاہئے کچھ چیزیں کے بارے میں سوالات، ہو سکتا ہے کھاؤ اور پیو. سب سے زیادہ مسائل پر جواب نہیں ہیں.'),
(3,'so', 'Diyaarinta waayo naasnuujinta', 'Haddii aadan naaska ka hor, waxaa laga yaabaa inaad qabtid su aalo ku saabsan waxyaabaha qaarkood, sida in naasaha ama naaska magooli qaabka dhib ma laha, haddii aad naas karo inkastoo hawlgallada naaska, awood u leh inay naaska la iska dhaxli karo ama waxa aad awoodo ama waa bay cunaan oo cabbaan. On arrimaha ugu, waxaa jira jawaabo.'),
(3,'lt', 'Pasirengimas žindymo','Jei neturite krūtimi, prieš, galbūt turite klausimų apie tam tikrus dalykus, pavyzdžiui, ar krūtys ar krūties bud formos nesvarbu, ar galite maitinti krūtimi, nepaisant krūtų operacijų, gebėjimas žindyti gali būti paveldima arba ką gali ar turėtų valgyti ir gerti. Daugeliu klausimų, yra atsakymus.'),
(4, 'en', 'Preparation for breastfeeding ','Some women are worried about breast buds their are too big, too small or too flat to trouble breastfeeding. The shape of the breast buds does not really matter as long as they and tissue surrounding them are stretchy. On rare occasions they are called introspective and slips away when you try to pull them out. But this does not necessarily preclude successful breastfeeding.'),
(4,'nb', 'Forberedelse til amming','Noen kvinner er bekymret for brystknopper deres er for store, for små eller for flat til problemer amming. Formen på brystknopper spiller egentlig ingen rolle så lenge de og vevet rundt dem er stretchy. I sjeldne tilfeller blir de kalt innadvendte og glipper unna når du prøver å trekke dem ut. Men dette betyr ikke nødvendigvis utelukker vellykket amming.'),
(4,'ar', 'استعدادا للرضاعة', 'بعض النساء قلقون براعم الثدي هي كبيرة جدا، صغيرة جدا جدا أو شقة لمشكلة الرضاعة الطبيعية. شكل براعم الثدي لا يهم حقا طالما أنها والأنسجة المحيطة بها وبسط. في مناسبات نادرة يطلق عليها الاستقراء وينزلق بعيدا عندما حاولت تسحبهم. ولكن هذا لا يمنع بالضرورة الرضاعة الطبيعية الناجحة.'),
(4, 'pl', 'Przygotowanie do karmienia piersią','Niektóre kobiety są zaniepokojeni ich pąki piersi są za duże, za małe lub zbyt płaska do bezproblemowego karmienia piersią. Kształt pączków piersi naprawdę nie ma znaczenia tak długo, jak i tkanki wokół nich są rozciągliwe. W rzadkich przypadkach są one nazywane introspekcji i wymyka się podczas próby je wysunąć. Ale to nie wyklucza udanego karmienia piersią.'),
(4, 'ur', 'دودھ پلانے کے لئے تیاری', 'کچھ خواتین میں چھاتی کے کلیوں کے بارے میں فکر مند ہیں ان کے، بہت بڑا اور بہت چھوٹا یا مصیبت دودھ پلانے کے لئے بہت فلیٹ ہیں. چھاتی کے کلیوں کی شکل واقعی کوئی فرق جب تک وہ اور ان کے ارد گرد کے ٹشو لچکیلا ہیں کے طور پر نہیں کرتا. نادر مواقع پر وہ مننشیل بلایا اور تم نے ان کو باہر نکالنے کی کوشش کریں جب دور تخم رہے ہیں. لیکن یہ ضروری کامیاب دودھ پلانے بند کرنا نہیں ہے.'),
(4,'so', 'Diyaarinta waayo naasnuujinta', 'Dumarka qaarkood ka walwalsan tahay hari naaska waa mid aad u weyn, aad u yar ama aad u guri si dhib naas-nuujinta. Qaabka uu ku hari naaska ma run ahaantii arrinta inta ay iyaga iyo nudaha ku hareereysan yihiin stretchy. Marar dhif ah waxaa la yidhaahdaa, u fiirsasho iyo iska daabka marka aad isku daydo in Iyaga u soo bixi. Laakiin tani ma uusan daruuri joogsanayn, naas-nuujinta si guul leh.'),
(4, 'lt', 'Pasirengimas žindymo','Kai kurios moterys yra susirūpinę krūties pumpurų jų yra per didelis, per mažas arba per butas problemų maitinant krūtimi. Krūties pumpurų forma tikrai ne klausimas, kaip ilgai, kaip jie ir audinių aplink jas yra tampri. Retais atvejais jie vadinami kontempliatyvus ir ūgliai toli, kai bandote juos ištraukti. Bet tai nebūtinai užkerta kelią sėkmingą žindymą.'),
(5, 'en','Preparation for breastfeeding ','Some women find that they get gestational streaks (striae) on the breasts, which you can also get the stomach and thighs. Some believe that such streaks can be prevented if one massaging the skin lightly with egg, olive oil or lotion. The effect is not scientifically proven, but this old wives tale is at least both conveniently and harmless.'),
(5, 'nb', 'Forberedelse til amming','Noen kvinner opplever at de får svangerskaps striper (striae) på brystene, noe som du også kan få magen og lårene. Noen mener at slike striper kan forebygges hvis man masserer huden lett med egg, olivenolje eller lotion. Effekten er ikke vitenskapelig bevist, men denne gamle kjerringrådet er minst både praktisk og ufarlig.'),
(5, 'ar', 'استعدادا للرضاعة','تجد بعض النساء أن تحصل الشرائط الحمل (السطور) على الصدور، والذي يمكنك أيضا الحصول على المعدة والفخذين. يعتقد البعض أن هذه الشرائط يمكن منعها إذا كان أحد تدليك البشرة برفق مع البيض وزيت الزيتون أو محلول. لم يثبت علميا تأثير، ولكن قصة هذا عجائز على الأقل على حد سواء مريح وغير ضارة.'),
(5, 'pl', 'Przygotowanie do karmienia piersią','Niektóre kobiety uważają, że się na nich smugi ciążową (rozstępy) na piersi, które można również uzyskać brzucha i ud. Niektórzy uważają, że takie pasy mogą być możliwa, gdy jedna masowanie skóry delikatnie jajka, olej z oliwek lub płynu. Efekt nie jest udowodnione naukowo, ale historia tego starego żon jest co najmniej zarówno wygodnie i nieszkodliwe.'),
(5, 'so', 'Diyaarinta waayo naasnuujinta','Dumarka qaarkood ka heli in ay hesho oo xariijimo uurka (gubtey) on laabta, oo waxaad sidoo kale ka heli kartaa caloosha iyo bowdyaha. Qaar ka mid ah waxay aaminsan yihiin in xariijimo sida looga hortegi karaa haddii qof salsalaaxdo maqaarka khafiif ah beed, oo saliid saytuun ah ama looshan. Saamaynta waa cilmi aan la xaqiijiyay, laakiin Sheeko this naagood jir waa ugu yaraan labada ku haboon oo u sir la aada.'),
(5, 'ur', '', ''),
(5, 'lt', 'Pasirengimas žindymo','Kai kurios moterys mano, kad jie gauna gestacinis dryželiais (laimėje) dėl krūtų, o taip pat galite gauti skrandžio ir šlaunų. Vieni mano, kad tokie ruožai gali būti užkirstas kelias, jei vienas masažuoti odą švelniai su kiaušinio, alyvuogių aliejaus ar losjonu. Poveikis nėra moksliškai įrodyta, bet tai bobų pasakos yra bent tiek patogiai ir nekenksmingas.');


-- --------------------------------------------------------

--
-- Structure de la table `language`
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `code` varchar(2) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `language`
--

TRUNCATE TABLE `language`;
--
-- Contenu de la table `language`
--

INSERT INTO `language` (`code`, `name`) VALUES('en', 'English'),
('nb', 'Norsk bokmål'),
('pl', 'Polszczyzna'),
('so', 'Soomaaliga'),
('ar', 'العربية'),
('lt', 'lietuvių kalba'),
('ur', 'اردو');

-- --------------------------------------------------------

--
-- Structure de la table `period`
--

DROP TABLE IF EXISTS `period`;
CREATE TABLE IF NOT EXISTS `period` (
  `id` int(11) NOT NULL,
  `image` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `period`
--

TRUNCATE TABLE `period`;
--
-- Contenu de la table `period`
--

INSERT INTO `period` (`id`, `image`) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL),
(5, NULL),
(6, NULL),
(7, NULL),
(8, NULL),
(9, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `period_tr`
--

DROP TABLE IF EXISTS `period_tr`;
CREATE TABLE IF NOT EXISTS `period_tr` (
  `period_id` int(11) NOT NULL,
  `language_code` varchar(2) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`period_id`,`language_code`),
  KEY `period_tr_ibfk_2` (`language_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `period_tr`
--

TRUNCATE TABLE `period_tr`;
--
-- Contenu de la table `period_tr`
--

INSERT INTO `period_tr` (`period_id`, `language_code`, `name`) VALUES
(1, 'en', 'During pregnancy'),
(2, 'en', '0-2 weeks'),
(3, 'en', '1-2 months'),
(4, 'en', '3-4 months'),
(5, 'en', '5-6 months'),
(6, 'en', '7-9 months'),
(7, 'en', '10-12 months'),
(8, 'en', '13-15 months'),
(9, 'en', '16-26 months'),
(1, 'nb', 'Svangerskap'),
(2, 'nb', '0-2 Uker'),
(3, 'nb', '1-2 Måneder'),
(4, 'nb', '3-4 Måneder'),
(5, 'nb', '5-6 Måneder'),
(6, 'nb', '7-9 Måneder'),
(7, 'nb', '10-12 Måneder'),
(8, 'nb', '13-15 Måneder'),
(9, 'nb', '16-26 Måneder'),
(1, 'ar', 'حمل'),
(2, 'ar', '0-2 أسابيع'),
(3, 'ar', '1-2 أشهر'),
(4, 'ar', '3-4 أشهر'),
(5, 'ar', '5-6 أشهر'),
(6, 'ar', '7-9 أشهر'),
(7, 'ar', '10-12 أشهر'),
(8, 'ar', '13-15 أشهر'),
(9, 'ar', '16-26 أشهر'),
(1, 'pl', 'Ciąża'),
(2, 'pl', '0-2 Tydzień'),
(3, 'pl', '1-2 Miesiące'),
(4, 'pl', '3-4 Miesiące'),
(5, 'pl', '5-6 Miesiące'),
(6, 'pl', '7-9 Miesiące'),
(7, 'pl', '10-12 Miesiące'),
(8, 'pl', '13-15 Miesiące'),
(9, 'pl', '16-26 Miesiące'),
(1, 'ur', 'حمل'),
(2, 'ur', '0-2 ہفتے'),
(3, 'ur', '1-2 مہینے'),
(4, 'ur', '3-4 مہینے'),
(5, 'ur', '5-6 مہینے'),
(6, 'ur', '7-9 مہینے'),
(7, 'ur', '10-12 مہینے'),
(8, 'ur', '13-15 مہینے'),
(9, 'ur', '16-26 مہینے'),
(1, 'so', 'Uurka'),
(2, 'so', '0-2 Todobaad'),
(3, 'so', '1-2 Bilood'),
(4, 'so', '3-4 Bilood'),
(5, 'so', '5-6 Bilood'),
(6, 'so', '7-9 Bilood'),
(7, 'so', '10-12 Bilood'),
(8, 'so', '13-15 Bilood'),
(9, 'so', '16-26 Bilood'),
(1, 'lt', 'nėštumas'),
(2, 'lt', '0-2 Savaites'),
(3, 'lt', '1-2 Mėnesių'),
(4, 'lt', '3-4 Mėnesių'),
(5, 'lt', '5-6 Mėnesių'),
(6, 'lt', '7-9 Mėnesių'),
(7, 'lt', '10-12 Mėnesių'),
(8, 'lt', '13-15 Mėnesių'),
(9, 'lt', '16-26 Mėnesių');

-- --------------------------------------------------------

--
-- Structure de la table `topic`
--

DROP TABLE IF EXISTS `topic`;
CREATE TABLE IF NOT EXISTS `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Vider la table avant d'insérer `topic`
--

TRUNCATE TABLE `topic`;
--
-- Contenu de la table `topic`
--

INSERT INTO `topic` (`id`, `image`) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL),
(5, NULL),
(6, NULL),
(7, NULL),
(8, NULL),
(9, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `topic_tr`
--

DROP TABLE IF EXISTS `topic_tr`;
CREATE TABLE IF NOT EXISTS `topic_tr` (
  `topic_id` int(11) NOT NULL,
  `language_code` varchar(2) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`topic_id`,`language_code`),
  KEY `topic_tr_ibfk_2` (`language_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `topic_tr`
--

TRUNCATE TABLE `topic_tr`;
--
-- Contenu de la table `topic_tr`
--

INSERT INTO `topic_tr` (`topic_id`, `language_code`, `name`) VALUES
(1, 'en', 'Benefits of breastfeeding'),
(2, 'en', 'Preparation for breastfeeding'),
(3, 'en', 'Motivation for breastfeeding'),
(4, 'en', 'Anatomy of the breast'),
(5, 'en', 'Previous negative breastfeeding experiences ?'),
(6, 'en', 'Has the mother had any operation of the breast ?'),
(7, 'en', 'Information about Ammehjelpen'),
(8, 'en', 'Information about Mother-child friendly hospitals'),
(9, 'en', 'Maternal food intake during breastfeeding'),
(1, 'nb', 'Fordeler med amming'),
(2, 'nb', 'Forberedelse til amming'),
(3, 'nb', 'Motivasjon for amming'),
(4, 'nb', 'Tidligere negative amming erfaringer?'),
(5, 'nb', 'Har mor hatt noen operasjon av brystet?'),
(6, 'nb', 'Informasjon om amming hjelp'),
(7, 'nb', 'Informasjon om mor-barn-vennlig sykehus'),
(8, 'nb', 'Maternal matinntaket under amming'),
(1, 'ar', 'فوائد الرضاعة الطبيعية'),
(2, 'ar', 'استعدادا للرضاعة'),
(3, 'ar', 'الدافع للرضاعة الطبيعية'),
(4, 'ar', 'السابقة الخبرات الرضاعة الطبيعية السلبية'),
(5, 'ar', 'وكانت الأم أي عملية من الثدي'),
(6, 'ar', 'معلومات عن المساعدة الرضاعة الطبيعية'),
(7, 'ar', 'معلومات عن المستشفيات صديقة الأم والطفل'),
(8, 'ar', 'الاستهلاك الغذائي للأم أثناء الرضاعة الطبيعية'),
(1, 'pl', 'Korzyści z karmienia piersią'),
(2, 'pl', 'Przygotowanie do karmienia piersią'),
(3, 'pl', 'Motywacja do karmienia piersią'),
(4, 'pl', 'Poprzednie negatywne doświadczenia piersią?'),
(5, 'pl', 'Czy matka miała żadnej operacji piersi?'),
(6, 'pl', 'Informacja o pomoc w karmieniu piersią'),
(7, 'pl', 'Informacja o przyjaznych dla matki i dziecka szpitalach'),
(8, 'pl', 'Maternal pokarmu w trakcie karmienia piersią'),
(1, 'ur', 'رضاعت کے فوائد'),
(2, 'ur', 'استعدادا للرضاعة'),
(3, 'ur', 'دودھ پلانے کے لئے پریرتا'),
(4, 'ur', 'پچھلا منفی رضاعت تجربات'),
(5, 'ur', 'ماں کی چھاتی کے کسی بھی آپریشن کو دیکھا گیا ہے'),
(6, 'ur', 'رضاعت کی مدد کے بارے میں معلومات'),
(7, 'ur', 'ماں بچے دوستانہ ہسپتالوں کے بارے میں معلومات'),
(8, 'ur', 'دودھ پلانے کے دوران زچگی کھانے کی مقدار'),
(1, 'so', 'Faa iidooyinka naas nuujinta'),
(2, 'so', 'Diyaarinta waayo naasnuujinta'),
(3, 'so', 'Rabitaan waayo naasnuujinta'),
(4, 'so', 'Waayo aragnimo hore naas xun?'),
(5, 'so', 'Miyuu hooyada lahaa howlgalka ka mid ah naaska?'),
(6, 'so', 'Warbixin ku saabsan kaalmo-nuujinta'),
(7, 'so', 'Warbixin ku saabsan isbitaalada saaxiibtinimo Hooyo-ilmaha'),
(8, 'so', 'Cunto qaadashada hooyada waqtiga nuujinta'),
(1, 'lt', 'Privalumai žindymo'),
(2, 'lt', 'Pasirengimas žindymo'),
(3, 'lt', 'Motyvacija žindymo'),
(4, 'lt', 'Ankstesnės neigiami žindymo patirtis?'),
(5, 'lt', 'Ar motina neturėjo krūties operacija?'),
(6, 'lt', 'Informacija apie žindymo pagalba'),
(7, 'lt', 'Informacija apie Motina-vaikams ligoninėse'),
(8, 'lt', 'Gimdyvių maistą žindymo laikotarpiu');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `association_period_topic`
--
ALTER TABLE `association_period_topic`
  ADD CONSTRAINT `association_period_topic_ibfk_1` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`),
  ADD CONSTRAINT `association_period_topic_ibfk_2` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`);

--
-- Contraintes pour la table `content`
--
ALTER TABLE `content`
  ADD CONSTRAINT `content_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`);

--
-- Contraintes pour la table `content_tr`
--
ALTER TABLE `content_tr`
  ADD CONSTRAINT `content_tr_ibfk_1` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  ADD CONSTRAINT `content_tr_ibfk_2` FOREIGN KEY (`language_code`) REFERENCES `language` (`code`);

--
-- Contraintes pour la table `period_tr`
--
ALTER TABLE `period_tr`
  ADD CONSTRAINT `period_tr_ibfk_1` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`),
  ADD CONSTRAINT `period_tr_ibfk_2` FOREIGN KEY (`language_code`) REFERENCES `language` (`code`);

--
-- Contraintes pour la table `topic_tr`
--
ALTER TABLE `topic_tr`
  ADD CONSTRAINT `topic_tr_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
  ADD CONSTRAINT `topic_tr_ibfk_2` FOREIGN KEY (`language_code`) REFERENCES `language` (`code`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
