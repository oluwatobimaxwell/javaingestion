package com.elastic.javaingestion.elastic.utils;
import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DataGenerator {
    String[] types = {"cell", "hdd"};
    String[] revisions = {"8.1.0 O11019 BCDEFH-200224V237"};
    String[] connection_types = {"USB Cable", "GSM", "CDMA", "LTE", "WiMAX"};
    String[] contact_memory = {"Phone", "SIM", "Email"};
    String[] contact_designation = {"Mobile", "Home", "Work", "Other"};
    String[] email_providers = {"gmail.com", "ymail.com", "hotmail.com", "yahoo.co.uk", "yahoo.com"};
    String[] network_providers = {"9mobile APN", "MTN NG", "GLO", "Airtel"};
    //Faker faker = new Faker(Locale.ENGLISH);
    Faker faker = new Faker(new Locale("en-US"));


    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getRevisions() {
        return revisions;
    }

    public void setRevisions(String[] revisions) {
        this.revisions = revisions;
    }

    public String[] getConnection_types() {
        return connection_types;
    }

    public void setConnection_types(String[] connection_types) {
        this.connection_types = connection_types;
    }

    public String[] getContact_memory() {
        return contact_memory;
    }

    public void setContact_memory(String[] contact_memory) {
        this.contact_memory = contact_memory;
    }

    public String[] getContact_designation() {
        return contact_designation;
    }

    public void setContact_designation(String[] contact_designation) {
        this.contact_designation = contact_designation;
    }

    public String[] getEmail_providers() {
        return email_providers;
    }

    public void setEmail_providers(String[] email_providers) {
        this.email_providers = email_providers;
    }

    public String[] getNetwork_providers() {
        return network_providers;
    }

    public void setNetwork_providers(String[] network_providers) {
        this.network_providers = network_providers;
    }

    public Faker getFaker() {
        return faker;
    }

    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public JSONObject selectedDevices(){
        try {
            Path pth = Paths.get("/src/main/resources/static/selected-devices.json");
            //Path pth = Paths.get("./selected-devices.json");

            BufferedReader reader = Files.newBufferedReader(pth);
            StringBuilder str = new StringBuilder();
            reader.lines().forEach(line -> { str.append(line); });
//            System.out.println("JSON READER: "+str);
            JSONArray data = new JSONArray(str.toString());
            return (JSONObject) data.get(randomInt(0, data.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = "[\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"5S\",\n" +
                "\t\t\"device\": \"TECNO-A571LS\",\n" +
                "\t\t\"model\": \"TECNO A571LS\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"A7S\",\n" +
                "\t\t\"device\": \"TECNO-A7S\",\n" +
                "\t\t\"model\": \"TECNO-A7S\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"C5\",\n" +
                "\t\t\"device\": \"TECNO-C5\",\n" +
                "\t\t\"model\": \"TECNO-C5\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"C7\",\n" +
                "\t\t\"device\": \"TECNO-C7\",\n" +
                "\t\t\"model\": \"TECNO-C7\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"C8\",\n" +
                "\t\t\"device\": \"TECNO-C8\",\n" +
                "\t\t\"model\": \"TECNO-C8\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"C9\",\n" +
                "\t\t\"device\": \"TECNO-C9\",\n" +
                "\t\t\"model\": \"TECNO-C9\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"C9S\",\n" +
                "\t\t\"device\": \"TECNO-C9\",\n" +
                "\t\t\"model\": \"TECNO-C9S\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"CAMON 11\",\n" +
                "\t\t\"device\": \"TECNO-CF7\",\n" +
                "\t\t\"model\": \"TECNO CF7\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"brand\": \"Tecno\",\n" +
                "\t\t\"name\": \"CAMON 11\",\n" +
                "\t\t\"device\": \"TECNO-CF7\",\n" +
                "\t\t\"model\": \"TECNO CF7S\"\n" +
                "\t}]";
        JSONArray data = new JSONArray(json);
        return (JSONObject) data.get(randomInt(0, data.length() - 1));
    }


    public int randomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    private long randomLong(long min) {
        Random r = new Random();
        return Math.abs(r.nextLong() + min);
    }

    public DateTime _datetime(){
        Random r = new Random();
        long t1 = System.currentTimeMillis() + r.nextInt();
        long t2 = t1 + 2 * 60 * 1000 + r.nextInt(60 * 1000) + 1;
        return new DateTime(t2);
    }

    public Long _datetimemilli(){
        Random r = new Random();
        long t1 = System.currentTimeMillis() + r.nextInt();
        long t2 = t1 + 2 * 60 * 1000 + r.nextInt(60 * 1000) + 1;
        return t2;
    }

    public DateTime timeStamp(){
        return new DateTime(System.currentTimeMillis());
    }

    public String getFromList(String[] options){
        return (options.length > 0) ? options[randomInt(0, options.length - 1)] : "";
    }

    public JSONObject randomePhoneName(){
            String phone =  String.format(getFromList(new String[]{"080","081", "070", "090", "070", "060"})+"%1d%03d%04d",
            (int) Math.floor(9*Math.random() + 1),
            (int) Math.floor(999*Math.random()),
            (int) Math.floor(9999*Math.random()));
            String name = faker.name().fullName();

            return new JSONObject().put("phone", phone).put("name", name);
    }

    public JSONObject personPhone(){
        try{
            String str = "[{\"phone\":\"08171235871\",\"name\":\"Anthony Satterfield\"}, {\"phone\":\"08013129616\",\"name\":\"Mr. Melva Kuhn\"}, {\"phone\":\"07056748001\",\"name\":\"Heriberto Hartmann\"}, {\"phone\":\"08067507825\",\"name\":\"Selena Pollich Sr.\"}, {\"phone\":\"09073647539\",\"name\":\"Benton Pollich\"}, {\"phone\":\"08017377554\",\"name\":\"Fredda Dicki\"}, {\"phone\":\"06038324633\",\"name\":\"Mrs. Renata Pacocha\"}, {\"phone\":\"08133687373\",\"name\":\"Vernell Moen\"}, {\"phone\":\"08073744668\",\"name\":\"Cheryle Spencer\"}, {\"phone\":\"08195094230\",\"name\":\"Israel Rodriguez\"}, {\"phone\":\"06068177466\",\"name\":\"Shad Walsh\"}, {\"phone\":\"08125868633\",\"name\":\"Lavonna Sauer\"}, {\"phone\":\"09052866158\",\"name\":\"Sophie Stroman\"}, {\"phone\":\"08145577924\",\"name\":\"Yee Von\"}, {\"phone\":\"07060878238\",\"name\":\"Miss Olen Swaniawski\"}, {\"phone\":\"07023684926\",\"name\":\"Wilma Schmitt II\"}, {\"phone\":\"08084180834\",\"name\":\"Mr. Wyatt Gusikowski\"}, {\"phone\":\"07018864904\",\"name\":\"Willis Cummings\"}, {\"phone\":\"08187376401\",\"name\":\"Hong Crist DVM\"}, {\"phone\":\"09062069007\",\"name\":\"Makeda Daugherty\"}, {\"phone\":\"08065183930\",\"name\":\"Corrie Leffler\"}, {\"phone\":\"08123037366\",\"name\":\"Dr. Philip Bernhard\"}, {\"phone\":\"09027234328\",\"name\":\"Bart Aufderhar\"}, {\"phone\":\"08093939655\",\"name\":\"Mr. Jaymie McClure\"}, {\"phone\":\"08116356871\",\"name\":\"Elayne Zemlak\"}, {\"phone\":\"07088635298\",\"name\":\"Dr. Wayne Welch\"}, {\"phone\":\"07067236581\",\"name\":\"Dexter Hessel\"}, {\"phone\":\"08044439495\",\"name\":\"Louie Ratke\"}, {\"phone\":\"08089839996\",\"name\":\"Hope King\"}, {\"phone\":\"09052345018\",\"name\":\"Eda Crooks\"}, {\"phone\":\"09096549125\",\"name\":\"Carli Feest\"}, {\"phone\":\"08114883072\",\"name\":\"Kami Cruickshank\"}, {\"phone\":\"06054180372\",\"name\":\"Dr. Monserrate Casper\"}, {\"phone\":\"08023789669\",\"name\":\"Miss Marcy Predovic\"}, {\"phone\":\"09080826904\",\"name\":\"Wes Frami\"}, {\"phone\":\"08086966711\",\"name\":\"Adele Bogan\"}, {\"phone\":\"06044019343\",\"name\":\"Rima McLaughlin\"}, {\"phone\":\"07052829449\",\"name\":\"Odis Schaden\"}, {\"phone\":\"06040150660\",\"name\":\"Leroy Johns\"}, {\"phone\":\"08110214182\",\"name\":\"Jefferey Shields\"}, {\"phone\":\"07075676980\",\"name\":\"Moriah Schmitt Jr.\"}, {\"phone\":\"07056231836\",\"name\":\"Demetrius Herzog\"}, {\"phone\":\"08058416402\",\"name\":\"Faustino Farrell\"}, {\"phone\":\"09018444684\",\"name\":\"Mrs. Lanette Schroeder\"}, {\"phone\":\"08192723050\",\"name\":\"Xavier Hoeger\"}, {\"phone\":\"07015944595\",\"name\":\"Indira Kutch\"}, {\"phone\":\"06043923436\",\"name\":\"Hung Waters\"}, {\"phone\":\"06023292192\",\"name\":\"Christoper Yost III\"}, {\"phone\":\"08147376680\",\"name\":\"Johnny Cartwright\"}, {\"phone\":\"08132836835\",\"name\":\"Rudolph Batz\"}, {\"phone\":\"07043829321\",\"name\":\"Simon Deckow\"}, {\"phone\":\"07055147665\",\"name\":\"Santos Schimmel\"}, {\"phone\":\"06026629356\",\"name\":\"Freddy Reilly\"}, {\"phone\":\"07043275887\",\"name\":\"Kelsey Reichel\"}, {\"phone\":\"07027302686\",\"name\":\"Ayanna Treutel\"}, {\"phone\":\"07034968162\",\"name\":\"Ms. Aletha Daugherty\"}, {\"phone\":\"08024338396\",\"name\":\"Renaldo Johnston\"}, {\"phone\":\"08027460281\",\"name\":\"Luther Crona\"}, {\"phone\":\"07084937279\",\"name\":\"Louella Berge\"}, {\"phone\":\"06032219373\",\"name\":\"Jarred O'Keefe\"}, {\"phone\":\"09022860558\",\"name\":\"Salvatore Welch\"}, {\"phone\":\"06051621588\",\"name\":\"Miss Errol Huel\"}, {\"phone\":\"07024183171\",\"name\":\"Bret Collier\"}, {\"phone\":\"08057270600\",\"name\":\"Dustin Erdman DVM\"}, {\"phone\":\"07030597920\",\"name\":\"Domenica Langosh\"}, {\"phone\":\"09088120415\",\"name\":\"Juliette Stroman\"}, {\"phone\":\"07053563485\",\"name\":\"Valencia Goodwin\"}, {\"phone\":\"07028673692\",\"name\":\"Youlanda Koelpin\"}, {\"phone\":\"06063940705\",\"name\":\"Madalene Becker\"}, {\"phone\":\"09040744306\",\"name\":\"Rosendo Blanda Jr.\"}, {\"phone\":\"07020377574\",\"name\":\"Ms. Edwardo Connelly\"}, {\"phone\":\"06048469997\",\"name\":\"Erminia Lebsack\"}, {\"phone\":\"09055154153\",\"name\":\"Silas Ledner\"}, {\"phone\":\"07023927847\",\"name\":\"Kendall Champlin\"}, {\"phone\":\"07039474034\",\"name\":\"Kip Langworth\"}, {\"phone\":\"07021091802\",\"name\":\"Belen MacGyver\"}, {\"phone\":\"09089569143\",\"name\":\"Yulanda Muller\"}, {\"phone\":\"07098337720\",\"name\":\"Augusta Shanahan\"}, {\"phone\":\"09013049448\",\"name\":\"Tracy Ritchie\"}, {\"phone\":\"09054047324\",\"name\":\"Paulina Gibson\"}, {\"phone\":\"09068498672\",\"name\":\"Loyce Hauck\"}, {\"phone\":\"09036808006\",\"name\":\"Ervin Jast\"}, {\"phone\":\"08143801274\",\"name\":\"Sadye Gerlach\"}, {\"phone\":\"06050770767\",\"name\":\"Katheleen Huels\"}, {\"phone\":\"08036544860\",\"name\":\"Elena Bechtelar\"}, {\"phone\":\"09077413492\",\"name\":\"Emerson Kertzmann\"}, {\"phone\":\"07036725745\",\"name\":\"Arturo Lemke Sr.\"}, {\"phone\":\"07038941453\",\"name\":\"Doyle Bashirian\"}, {\"phone\":\"06014523619\",\"name\":\"Emilio Boyle III\"}, {\"phone\":\"07033371267\",\"name\":\"Erich Toy\"}, {\"phone\":\"07076574233\",\"name\":\"Tanna Crooks\"}, {\"phone\":\"07069718566\",\"name\":\"Joanie Barrows\"}, {\"phone\":\"07091626651\",\"name\":\"Alta Harris DDS\"}, {\"phone\":\"08111123372\",\"name\":\"Ivory Ryan\"}, {\"phone\":\"09061253905\",\"name\":\"Deanne Windler\"}, {\"phone\":\"09015950538\",\"name\":\"Nancy Hickle Sr.\"}, {\"phone\":\"09063132900\",\"name\":\"Tonja Feil\"}, {\"phone\":\"07031405785\",\"name\":\"Keneth Halvorson\"}, {\"phone\":\"07033591134\",\"name\":\"Tanja Monahan PhD\"}, {\"phone\":\"09070160566\",\"name\":\"Wally Doyle\"}, {\"phone\":\"09052404120\",\"name\":\"Weldon Jaskolski\"}, {\"phone\":\"07049811507\",\"name\":\"Ms. Xuan West\"}, {\"phone\":\"09026278522\",\"name\":\"Yanira Stanton III\"}, {\"phone\":\"08165102337\",\"name\":\"Allison Schaden\"}, {\"phone\":\"08138181715\",\"name\":\"Sudie McKenzie\"}, {\"phone\":\"07036368027\",\"name\":\"Kelley Jacobs DDS\"}, {\"phone\":\"08137558957\",\"name\":\"Tajuana Douglas MD\"}, {\"phone\":\"09072702436\",\"name\":\"Dayle Leuschke\"}, {\"phone\":\"06071224480\",\"name\":\"Na Kutch\"}, {\"phone\":\"06035731941\",\"name\":\"Lorrie Gusikowski Jr.\"}, {\"phone\":\"08047672585\",\"name\":\"Jame Boehm\"}, {\"phone\":\"06029338481\",\"name\":\"Corinna Little\"}, {\"phone\":\"09026069404\",\"name\":\"Novella Mitchell PhD\"}, {\"phone\":\"07042765989\",\"name\":\"Demetra Schmeler\"}, {\"phone\":\"08158395100\",\"name\":\"Everett MacGyver PhD\"}, {\"phone\":\"08058124519\",\"name\":\"Douglass Bechtelar\"}, {\"phone\":\"09087070191\",\"name\":\"Danyelle Brekke\"}, {\"phone\":\"08019796305\",\"name\":\"Lincoln Davis\"}, {\"phone\":\"08090698273\",\"name\":\"Monty Towne\"}, {\"phone\":\"08151227681\",\"name\":\"Errol Rogahn\"}, {\"phone\":\"06019650616\",\"name\":\"Tijuana O'Conner IV\"}, {\"phone\":\"09015370117\",\"name\":\"Antone Reilly\"}, {\"phone\":\"09087946077\",\"name\":\"Jeanett Lakin\"}, {\"phone\":\"07027812642\",\"name\":\"Rochell Barrows\"}, {\"phone\":\"08180482412\",\"name\":\"Jamel Wyman\"}, {\"phone\":\"08165996712\",\"name\":\"Peter Crist\"}, {\"phone\":\"08014782342\",\"name\":\"Gilberto Breitenberg\"}, {\"phone\":\"06015812894\",\"name\":\"Mrs. Landon Kuhn\"}, {\"phone\":\"07062949788\",\"name\":\"Avis Zboncak\"}, {\"phone\":\"06055396914\",\"name\":\"Jerrold Prosacco\"}, {\"phone\":\"07083788318\",\"name\":\"Vergie Lesch\"}, {\"phone\":\"08037840367\",\"name\":\"Santiago Kreiger\"}, {\"phone\":\"06026455269\",\"name\":\"Ms. Danille Stroman\"}, {\"phone\":\"09068058843\",\"name\":\"Lucretia Gislason\"}, {\"phone\":\"06025251062\",\"name\":\"Alton Cassin\"}, {\"phone\":\"07044697416\",\"name\":\"Dorothy Blick\"}, {\"phone\":\"08061013964\",\"name\":\"Adele Jerde\"}, {\"phone\":\"06046420258\",\"name\":\"Marguerita Gorczany III\"}, {\"phone\":\"06057376018\",\"name\":\"Providencia Rodriguez\"}, {\"phone\":\"06031667694\",\"name\":\"Cris Moore\"}, {\"phone\":\"06080405953\",\"name\":\"Freddy Dare PhD\"}, {\"phone\":\"08043510224\",\"name\":\"Aubrey Hyatt\"}, {\"phone\":\"09096709735\",\"name\":\"Ms. Carolee Romaguera\"}, {\"phone\":\"08076084941\",\"name\":\"Lois DuBuque\"}, {\"phone\":\"08090628911\",\"name\":\"Marivel Cormier\"}, {\"phone\":\"06095365398\",\"name\":\"Penelope Torp Sr.\"}, {\"phone\":\"07031531066\",\"name\":\"Diego Sanford\"}, {\"phone\":\"09046411209\",\"name\":\"Fredia Hagenes\"}, {\"phone\":\"08192015628\",\"name\":\"Birgit Hilll\"}, {\"phone\":\"09040158702\",\"name\":\"Miss Buster Willms\"}, {\"phone\":\"07043101790\",\"name\":\"Shaina Turcotte\"}, {\"phone\":\"08036128547\",\"name\":\"Shae Adams\"}, {\"phone\":\"07076283576\",\"name\":\"Morgan Gulgowski\"}, {\"phone\":\"08173200655\",\"name\":\"Gonzalo Johns\"}, {\"phone\":\"07021990039\",\"name\":\"Bradley Brakus\"}, {\"phone\":\"08167900673\",\"name\":\"Kenneth Rolfson\"}, {\"phone\":\"09042306166\",\"name\":\"Zack Hilll\"}, {\"phone\":\"09069896125\",\"name\":\"Felecia Hagenes\"}, {\"phone\":\"07089772425\",\"name\":\"Jeanett Ward DVM\"}, {\"phone\":\"09096283641\",\"name\":\"Alycia Bechtelar\"}, {\"phone\":\"07066777848\",\"name\":\"Eustolia Turner\"}, {\"phone\":\"06090671816\",\"name\":\"Marcel McGlynn\"}, {\"phone\":\"08188373272\",\"name\":\"Miss Tiara Runte\"}, {\"phone\":\"09019227058\",\"name\":\"Coralee Hermiston\"}, {\"phone\":\"07038467700\",\"name\":\"Daniel Jones PhD\"}, {\"phone\":\"07024992199\",\"name\":\"Miss Mike DuBuque\"}, {\"phone\":\"07022031848\",\"name\":\"Bernard Stroman\"}, {\"phone\":\"07061899836\",\"name\":\"Jarvis Weber\"}, {\"phone\":\"07097288652\",\"name\":\"Michal Lowe\"}, {\"phone\":\"06053744226\",\"name\":\"Dr. Ettie Wunsch\"}, {\"phone\":\"06089350996\",\"name\":\"Dr. Keven VonRueden\"}, {\"phone\":\"06097957035\",\"name\":\"Jone Krajcik\"}, {\"phone\":\"08161882000\",\"name\":\"Trent Quitzon\"}, {\"phone\":\"09041613553\",\"name\":\"Takako Harris\"}, {\"phone\":\"08065573067\",\"name\":\"Danuta Brakus\"}, {\"phone\":\"07056796792\",\"name\":\"Klara Braun\"}, {\"phone\":\"06051969352\",\"name\":\"Treasa Kemmer\"}, {\"phone\":\"08014117569\",\"name\":\"Modesto Baumbach I\"}, {\"phone\":\"07057091542\",\"name\":\"Reita Ritchie\"}, {\"phone\":\"08046998893\",\"name\":\"Man Mitchell\"}, {\"phone\":\"09054066662\",\"name\":\"Ms. Stefan D'Amore\"}, {\"phone\":\"06055852999\",\"name\":\"Janie Thompson\"}, {\"phone\":\"08043759259\",\"name\":\"Matthew Koch\"}, {\"phone\":\"06039198978\",\"name\":\"Aaron Hammes\"}, {\"phone\":\"07021997486\",\"name\":\"Michale Larson\"}, {\"phone\":\"07029478351\",\"name\":\"Annelle Block\"}, {\"phone\":\"08155906622\",\"name\":\"Kari Mayert\"}, {\"phone\":\"08014124920\",\"name\":\"Nickolas Koelpin\"}, {\"phone\":\"08095615129\",\"name\":\"Antony Tromp\"}, {\"phone\":\"08177123985\",\"name\":\"Felix Quitzon\"}, {\"phone\":\"08136445711\",\"name\":\"Miss Keila O'Hara\"}, {\"phone\":\"08093554403\",\"name\":\"Stewart McCullough\"}, {\"phone\":\"09064235811\",\"name\":\"Stacey Aufderhar II\"}, {\"phone\":\"09088705075\",\"name\":\"Minh Gutkowski Jr.\"}, {\"phone\":\"09010228677\",\"name\":\"Caterina Gerlach\"}, {\"phone\":\"09085085214\",\"name\":\"Jasmine Deckow\"}, {\"phone\":\"07073257519\",\"name\":\"Carolee Emard\"}, {\"phone\":\"08163875239\",\"name\":\"Dr. Darin Weber\"}, {\"phone\":\"08185761326\",\"name\":\"Miss Hui Torp\"}, {\"phone\":\"07043652276\",\"name\":\"Brande Cummerata\"}, {\"phone\":\"06065321675\",\"name\":\"Annelle Donnelly\"}, {\"phone\":\"07036941109\",\"name\":\"Sena Bode\"}, {\"phone\":\"08186795294\",\"name\":\"Loyd Nikolaus\"}, {\"phone\":\"08025377949\",\"name\":\"Dr. Toi Waelchi\"}, {\"phone\":\"06099757284\",\"name\":\"Earle Howell\"}, {\"phone\":\"06026740616\",\"name\":\"Jackie Emmerich\"}, {\"phone\":\"09063544701\",\"name\":\"Shawn Huel\"}, {\"phone\":\"07031854618\",\"name\":\"Ms. Lacy Kuvalis\"}, {\"phone\":\"09066279705\",\"name\":\"Carrol Rice\"}, {\"phone\":\"06075530257\",\"name\":\"Miss Xuan Hirthe\"}, {\"phone\":\"07046175696\",\"name\":\"Everett Monahan\"}, {\"phone\":\"07075571088\",\"name\":\"Tracy Volkman PhD\"}, {\"phone\":\"08081978685\",\"name\":\"Angelo Ward\"}, {\"phone\":\"07034079133\",\"name\":\"Miss Eugene Kuhlman\"}, {\"phone\":\"07095529777\",\"name\":\"Ms. Abigail Steuber\"}, {\"phone\":\"06099346821\",\"name\":\"Ma Conn III\"}, {\"phone\":\"08012592823\",\"name\":\"Emily Rippin\"}, {\"phone\":\"07021880016\",\"name\":\"Fanny Kuphal\"}, {\"phone\":\"08138374302\",\"name\":\"Bradly Williamson\"}, {\"phone\":\"06055470529\",\"name\":\"Miss Timmy Quigley\"}, {\"phone\":\"08081820088\",\"name\":\"Roseann Mayer\"}, {\"phone\":\"06054820213\",\"name\":\"Normand Shanahan II\"}, {\"phone\":\"06027366062\",\"name\":\"Liliana Barrows\"}, {\"phone\":\"06042712146\",\"name\":\"Jacqui Durgan\"}, {\"phone\":\"06014936568\",\"name\":\"Holly Powlowski\"}, {\"phone\":\"07054147534\",\"name\":\"Nickolas Berge\"}, {\"phone\":\"07027926355\",\"name\":\"Mohammed Sanford\"}, {\"phone\":\"09091639709\",\"name\":\"Sherwood Reinger\"}, {\"phone\":\"08185308074\",\"name\":\"Freddy Morissette\"}, {\"phone\":\"09053859097\",\"name\":\"Cesar Kuhn\"}, {\"phone\":\"08187328415\",\"name\":\"Daniel Tromp\"}, {\"phone\":\"08151415171\",\"name\":\"Phillip Ondricka\"}, {\"phone\":\"09075231757\",\"name\":\"Charley Batz I\"}, {\"phone\":\"09025853266\",\"name\":\"Ms. Kandis Howell\"}, {\"phone\":\"06063060626\",\"name\":\"Rolland Lind Sr.\"}, {\"phone\":\"09083715653\",\"name\":\"Erminia Cummerata\"}, {\"phone\":\"08084547386\",\"name\":\"Rey Price\"}, {\"phone\":\"09074567489\",\"name\":\"Ms. Merri Wilkinson\"}, {\"phone\":\"08140801409\",\"name\":\"Sang Mayer IV\"}, {\"phone\":\"07071639186\",\"name\":\"Hayden Emmerich\"}, {\"phone\":\"08083370447\",\"name\":\"Mr. Sima Wunsch\"}, {\"phone\":\"09035407315\",\"name\":\"Rickie Hermann\"}, {\"phone\":\"09074204176\",\"name\":\"Renna Schmeler\"}, {\"phone\":\"09044446451\",\"name\":\"Genaro Abernathy DDS\"}, {\"phone\":\"08057761677\",\"name\":\"Lynna Abshire\"}, {\"phone\":\"09073994358\",\"name\":\"Ms. Kira Harris\"}, {\"phone\":\"07061543259\",\"name\":\"Ms. Jamey Leannon\"}, {\"phone\":\"09091082528\",\"name\":\"Kimbery Stiedemann\"}, {\"phone\":\"06039270233\",\"name\":\"Karissa McClure\"}, {\"phone\":\"08165393478\",\"name\":\"Nella Rolfson I\"}, {\"phone\":\"08186754204\",\"name\":\"Clark Powlowski\"}, {\"phone\":\"09098085207\",\"name\":\"Diego Jenkins\"}, {\"phone\":\"08082370088\",\"name\":\"Vicente Durgan\"}, {\"phone\":\"08095211964\",\"name\":\"Alane Gutkowski\"}, {\"phone\":\"07024674329\",\"name\":\"Mckinley Oberbrunner III\"}, {\"phone\":\"08129978860\",\"name\":\"Gerald Schaden\"}, {\"phone\":\"06055661856\",\"name\":\"Rico McKenzie\"}, {\"phone\":\"09046915982\",\"name\":\"Mr. Evelynn Gleichner\"}, {\"phone\":\"08163081956\",\"name\":\"Annette Tillman\"}, {\"phone\":\"08082715411\",\"name\":\"Ben Hagenes\"}, {\"phone\":\"08115964648\",\"name\":\"Irish Flatley\"}, {\"phone\":\"06082172067\",\"name\":\"Bernetta Lindgren\"}, {\"phone\":\"07096435196\",\"name\":\"Dr. Stefan Waelchi\"}, {\"phone\":\"08153266411\",\"name\":\"Benny Daugherty Jr.\"}, {\"phone\":\"08176115141\",\"name\":\"Mirella Auer\"}, {\"phone\":\"08013633924\",\"name\":\"Lindsey Funk\"}, {\"phone\":\"08128763705\",\"name\":\"Michel Schmitt\"}, {\"phone\":\"07070972770\",\"name\":\"Brigid Parisian\"}, {\"phone\":\"08050423491\",\"name\":\"Nicolas Keebler V\"}, {\"phone\":\"09041877329\",\"name\":\"Mose O'Keefe\"}, {\"phone\":\"09035739293\",\"name\":\"Isaiah Wisozk\"}, {\"phone\":\"08188741137\",\"name\":\"Miss Buddy Moen\"}, {\"phone\":\"08143858243\",\"name\":\"Bao Mohr I\"}, {\"phone\":\"09050878388\",\"name\":\"Terrell Rowe\"}, {\"phone\":\"09078193801\",\"name\":\"Mr. Jarvis McLaughlin\"}, {\"phone\":\"08174578474\",\"name\":\"Lael Von\"}, {\"phone\":\"07063894891\",\"name\":\"Tana Fisher\"}, {\"phone\":\"08148952923\",\"name\":\"Addie Wolff PhD\"}, {\"phone\":\"06052415124\",\"name\":\"Woodrow Cole II\"}, {\"phone\":\"08086618843\",\"name\":\"Miss Arnoldo Kirlin\"}, {\"phone\":\"07035875225\",\"name\":\"Afton Nikolaus\"}, {\"phone\":\"08094437280\",\"name\":\"Ms. Joleen Grant\"}, {\"phone\":\"07094244798\",\"name\":\"Isiah Kunde\"}, {\"phone\":\"07057358917\",\"name\":\"Marisha Huels Jr.\"}, {\"phone\":\"07075957123\",\"name\":\"Samual Haag\"}, {\"phone\":\"06067713317\",\"name\":\"Miss Carrol Goyette\"}, {\"phone\":\"09037263041\",\"name\":\"Anthony Heathcote\"}, {\"phone\":\"08124891256\",\"name\":\"Jarrett Rosenbaum MD\"}, {\"phone\":\"08027626944\",\"name\":\"Stephan Orn\"}, {\"phone\":\"08064452841\",\"name\":\"Angel Lynch\"}, {\"phone\":\"07017399639\",\"name\":\"Modesto Lehner II\"}, {\"phone\":\"09062036857\",\"name\":\"Lorenzo Sipes\"}, {\"phone\":\"07030609141\",\"name\":\"Lonny Sawayn\"}, {\"phone\":\"07056349279\",\"name\":\"Mrs. Lenny Cartwright\"}, {\"phone\":\"08158841600\",\"name\":\"Elba Stoltenberg DDS\"}, {\"phone\":\"07056131021\",\"name\":\"Allena Metz\"}, {\"phone\":\"09043260277\",\"name\":\"Athena Hand\"}, {\"phone\":\"09059006861\",\"name\":\"Isis Stamm\"}, {\"phone\":\"09089000928\",\"name\":\"Rachelle Blanda\"}, {\"phone\":\"06035509023\",\"name\":\"Vania Sipes\"}]";
            JSONArray arr = new JSONArray(str);
            return new JSONObject(arr.get(randomInt(0, arr.length() - 1)).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getEnglishSMS(){
        String d = "You feel like a beautiful backdrop to my life. You make me feel whole. I can’t imagine living a life without you. You are my reason to be. I feel like my life started on the day I met you. You’re a dream. When I wake up every morning, I pinch myself remembering that you’re in my life. I couldn’t even imagine being happy before I met you. Now I’m the vision of contentment.I will never stop loving you, and I’ll cherish you as long as I live.You’re my dream lover, and I couldn’t picture someone better suited for me than you. What could I have possibly done to deserve something as magical and beautiful as you? When you gaze over at me, I feel a serious case of butterflies.I feel as giddy thinking about you as I did the first day we met. My love and adoration for you only grows stronger with time. Much, much stronger.My number one goal in life is to make you happy. You deserve absolutely everything.I would give anything to make you smile. Your smile lights me up inside. Knowing that you’re by my side gives the strength to wake up every morning. I’m a much better person because of knowing love, and you’re why I know love. Being around you is the definition of pure love, joy and happiness.I didn’t know what love was until I first laid eyes on you.No one else on the planet could ever compare to you. You’re pure beauty. When I’m around you, I feel comfortable and totally at ease. You’re my soul mate. I was destined to be with you. Being around you always feels so right. When I see you, everything in the world feels perfect and pure. I adore you.If I could be with you forever, I’d be the happiest person alive. You complete me and I want to be able to do the same for you. Seeing you was love at first sight. Seeing you changed my life forever. If I can’t have you in my life, I don’t want anyone else. No on on the planet could ever compare to your beauty, kindness and overall wonder. Thank you for introducing love and beauty to my life. You’ve made me so happy. You’re my number one priority in life. I put you above anything and everything else. When I’m in your company, I feel like the luckiest person in the world. Sometimes observing your beauty in action makes me absolutely speechless. Words fail me. You carry the key to my heart and absolutely no one else can open it.What on earth could I have possibly ever done to deserve your beauty and grace? Your smile is the most angelic thing I’ve ever been fortunate enough to witness. When we’re together, nothing in the world can stop us, and I mean absolutely nothing.You’re the only person on the planet who fulfills me. You’re my other half.It’s you and me against the universe. We’re the strongest team there is. You make me want to make the world a better place. You’re perfect. I love every single thing about you. Everything about you is flawless. Being around you feels as surreal and wonderful as a dream.";
        List<String> myList = new ArrayList<String>(Arrays.asList(d.split(",")));
        return myList.get(randomInt(0, myList.size() - 1));
    }

    public String randomPhoneNumber(){
        String[] phones = new String[]{"07054641072", "08069129301", "07072525971", "08117189498", "07042969386", "07024152347", "08065567974", "07050064470", "07095472909", "09036966128", "08137349741", "06024027546", "08039077661", "06011789868", "09024899818", "08153014714", "09089365999", "09044260346", "08059403389", "08151690936", "08063733661", "08036366279", "06092788832", "08087117422", "07035040687", "08122213005", "08094091204", "08077727343", "07078714391", "07086524384", "07092683517", "06054578302", "07011375667", "08045977459", "07037626475", "08083671423", "07033768868", "07024632459", "09011177992", "08148011942", "06083330460", "08195716169", "09033655527", "08153124649", "07042682507", "08054390679", "08152386812", "07071639366", "07019953855", "06069411784", "06083478098", "07099957034", "07045054602", "08085592196", "08097453512", "06023215668", "07031968182", "07054153867", "06073314482", "07023967720", "09032389939", "08044171472", "06053534584", "09035775936", "07069211675", "08065025121", "08024312374", "07093553938", "08170008121", "08027954996", "07049243044", "06089630234", "07020643745", "06063433498", "07043458837", "08128150315", "09048636623", "08040981995", "08116308287", "09068610876", "07061520477", "06068779037", "06036205337", "06054833044", "09082116167", "07021855253", "09019031396", "07086457724", "08156150141", "08150516828", "08164116246", "07076505888", "08049769452", "08054074670", "09056427591", "08081879772", "08175611026", "09039349935", "06053083783", "09041657511"};
        return getFromList(phones);
    }

    public String randomString(int length, String prefix){
        String n = "";
        int l = length - prefix.length();
        for (int i = 0; i < l; i++) {
            n = n+"1";
        }
        long x = Long.parseLong(n);
        String s = String.valueOf(randomLong(x));
        return prefix + String.format("%."+ l +"s", s);
    }

    public String sha256(String text){
        String uuid = UUID.randomUUID().toString();
        return DigestUtils.sha256Hex(text+uuid).toUpperCase();
    }

    public String stringTimeStamp(){
        return _datetime().toString();
    }



}
