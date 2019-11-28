package system.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.FileWriter;
import java.io.Serializable;

import system.misc.*;
import system.exceptions.*;

/**
 * Classe que engloba todas as outras classes relevantes do projeto, e pelo qual o controle do sistema é feito.
 * @author Javi
 */
public class Core implements Serializable {
    
    private static final long serialVersionUID = 865249965546670245L;
    private ArrayList<ScoreRules> rules = new ArrayList<ScoreRules>();
    private ArrayList<Vehicle> vehs = new ArrayList<Vehicle>();
    private ArrayList<Qualify> qualifies = new ArrayList<Qualify>();
    private ArrayList<Post> posts = new ArrayList<Post>();
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private int credentYear;

    public Core() {
    }

    /**
     * Adiciona o parâmetro de entrada na lista de veículos do sistema, mas lança uma exceção caso ele já exista.
     * @param v Um "Vehicle" a ser adicionado no atributo "vehs"
     */
    public void addVehicle(Vehicle v) throws Exception {
        if (vehs.contains(v)) {
            throw new DuplicatedId(v.getCod());
        } else {
            vehs.add(v);
        }
    }

    /**
     * Adiciona o parâmetro de entrada na lista de professores do sistema, mas lança uma exceção caso ele já exista.
     * @param t Um "Teacher" a ser adicionado no atributo "teachers".
     */
    public void addTeacher(Teacher t) throws Exception {
        if (teachers.contains(t)) {
            throw new DuplicatedId(t.getId());
        } else {
            teachers.add(t);
        }
    }

    /**
     * Adiciona o parâmetro de entrada na lista de qualis do sistema.
     * @param q Um qualis a ser adicionado no atributo "qualifies".
     */
    public void addQualify(Qualify q) {
        qualifies.add(q);
    }

    /**
     * Adiciona o parâmetro de entrada na lista de posts do sistema.
     * @param p Um Post a ser adicionado no atributo "posts".
     */
    public void addPost(Post p) {
        posts.add(p);
    }

    /**
     * Lê o arquivo e formata suas informações para se adaquarem aos atributos da classe "Teacher",
     * além de guardar um objeto desta classe na lista de professores "teachers".
     * @param infile Arquivo do qual as informações da classe "Teacher" serão lidas.
     */
    // Import from file functions
    public void importTeacherFile(File infile) throws Exception {
        // Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] fields = line.split(";");

            if ((fields.length < 4) || (fields.length > 5)) {
                input.close();
                throw new FormatException();
            } else {
                Teacher obj = null;
                try {
                    long id = Long.parseLong(fields[0].trim());
                    String name = fields[1];
                    Date birth = Utils.convertDate(fields[2]);
                    Date entry = Utils.convertDate(fields[3]);
                    boolean isMajor = false;
                    if (fields.length == 5) {
                        if (fields[4].compareToIgnoreCase("X") == 0) {
                            isMajor = true;
                        }
                    }
                    obj = new Teacher(id, name, birth, entry, isMajor);
                    this.addTeacher(obj);
                } catch (Exception e) {
                    obj = null;
                    input.close();
                    throw e;
                }
            }
        }
        input.close();
    }

    /**
     * Lê o arquivo e formata suas informações para se adaquarem aos atributos da classe "Vehicle",
     * além de guardar um objeto desta classe na lista de veículos "vehs".
     * @param infile Arquivo do qual as informações da classe "Vehicle" serão lidas.
     */
    public void importVehicleFile(File infile) throws Exception {
        // Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] fields = line.split(";");

            if ((fields.length < 4) || (fields.length > 5)) {
                input.close();
                throw new FormatException();
            } else {
                Vehicle obj = null;
                try {
                    String cod = fields[0].trim();
                    String name = fields[1].trim();
                    char type = fields[2].charAt(0);
                    if ((type != 'C') && (type != 'P')) {
                        throw new UndefinedVehicle(cod, type);
                    }
                    float imp = Utils.commaFloatFromString(fields[3]);
                    String issn = "None";
                    if (fields.length == 5) {
                        issn = fields[4];
                    }
                    obj = new Vehicle(cod, name, type, imp, issn);
                    this.addVehicle(obj);
                } catch (Exception e) {
                    obj = null;
                    input.close();
                    throw e;
                }
            }
        }
        input.close();
    }

    /**
     * * Lê o arquivo e formata suas informações para se adaquarem aos atributos da classe "Post",
     * atribui os professores e veículos de cada post, o post em seu respectivo professor
     * e guarda um objeto da classe "post" na lista de posts "posts".
     * @param infile Arquivo do qual as informações da classe "Post" serão lidas.
     * @throws FormatException if the lenght of the line read is different than 9 or if the character on the
     * position of the type is neither equivalent to a Conference nor a periodic.
     * @throws UndefinedTeacher if the id read does not match any teacher on this instance of the class.
     * @throws UndefinedVehicle if the parameter read does not match any vehicle on this instance of the class.
     * 
     */
    public void importPostFile(File infile) throws Exception {
        // Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] fields = line.split(";");

            if (fields.length != 9) {
                input.close();
                throw new FormatException();
            } else {
                Post obj = null;
                int flag = -1;
                // Check if "Volume" field is empty
                if (fields[5].isEmpty()) {
                    // So It is a Conference
                    flag = 1;
                } else {
                    // Else It is a Periodic
                    flag = 0;
                }
                try {
                    // Getting and setting default fields
                    int year = Integer.parseInt(fields[0].trim());
                    String title = fields[2].trim();
                    int num = Integer.parseInt(fields[4].trim());
                    String veh = fields[1].trim();
                    int initPage = Integer.parseInt(fields[7].trim());
                    int endPage = Integer.parseInt(fields[8].trim());
                    if (flag == -1) {
                        throw new FormatException();
                    } else if (flag == 0) {
                        int volume = Integer.parseInt(fields[5].trim());
                        obj = new Periodic(year, num, title, initPage, endPage, volume);
                    } else {
                        String location = fields[6];
                        obj = new Conference(year, num, title, initPage, endPage, location);
                    }
                    // Setting teachers
                    ArrayList<Long> ids = Utils.stringToLongArray(fields[3]);
                    for (Long id : ids) {
                        Teacher t = getTeacher(id);
                        if (t == null) {
                            throw new UndefinedTeacher(title, id);
                        } else {
                            t.addPost(obj);
                            obj.addTeacher(t);
                        }
                    }
                    // Setting vehicle
                    Vehicle v = getVehicle(veh);
                    if (v == null) {
                        throw new UndefinedVehicle(title, veh);
                    }
                    v.addPost(obj);
                    obj.setVehicle(v);

                    // Adding to core system
                    this.posts.add(obj);
                } catch (Exception e) {
                    obj = null;
                    input.close();
                    throw e;
                }
            }
        }
        input.close();
    }

    /**
     * Lê o arquivo e formata suas informações para se adaquarem aos atributos da classe "Vehicle",
     * além de guardar um objeto desta classe na lista de veículos "vehs".
     * @param infile Arquivo do qual as informações da classe "Qualify" serão lidas.
     * @throws FormatException if lenght of line to be read is different from 3.
     * @throws UndefinedQualis if qualis read does not match one of the valid qualis.
     * @throws UndefinedVehicle if there is no vehicle with the code of the entry parameter.

     */
    public void importQualisFile(File infile) throws Exception {
        // Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] fields = line.split(";");

            if (fields.length != 3) {
                input.close();
                throw new FormatException();
            } else {
                Qualify qualify = null;
                try {
                    int year = Integer.parseInt(fields[0].trim());
                    String vehCod = fields[1].trim();
                    String qualis = fields[2].trim().toUpperCase();
                    if (!Qualify.checkQualis(qualis)) {
                        throw new UndefinedQualis(qualis, vehCod, year);
                    }
                    Vehicle veh = getVehicle(vehCod);
                    if (veh == null) {
                        throw new UndefinedVehicle(year, vehCod);
                    }
                    qualify = new Qualify(year, qualis, vehCod);
                    qualify.setVehCod(vehCod);
                    veh.addQualify(qualify);
                    this.qualifies.add(qualify);
                } catch (Exception e) {
                    qualify = null;
                    input.close();
                    throw e;
                }
            }
        }
        input.close();
    }

    /**
     * Lê o arquivo e formata suas informações para se adaquarem aos atributos da classe "ScoreRules",
     * além de guardar um objeto desta classe na lista de regras "rules".
     * @param infile Arquivo do qual as informações da classe "Qualify" serão lidas.
     * @throws FormatException if the lenght of the line is different than 7.
     * @throws UndefinedQualis if the qualis does not match one of the valid qualis.
     */
    public void importRuleFile(File infile) throws Exception {
        // Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] fields = line.split(";");

            if (fields.length != 7) {
                input.close();
                throw new FormatException();
            } else {
                ScoreRules obj = null;
                try {
                    Date startsOn = Utils.convertDate(fields[0]);
                    Date endsOn = Utils.convertDate(fields[1]);
                    String[] qualis = fields[2].split(",");
                    for (String quali : qualis) {
                        if (!Qualify.checkQualis(quali)) {
                            throw new UndefinedQualis(quali, startsOn);
                        }
                    }
                    String[] score = fields[3].split(",");
                    float multi = Utils.commaFloatFromString(fields[4]);
                    int years = Integer.parseInt(fields[5].trim());
                    int minScore = Integer.parseInt(fields[6].trim());

                    obj = new ScoreRules(startsOn, endsOn, multi, years, minScore);
                    obj.setQualis(qualis, score);
                    this.rules.add(obj);
                } catch (Exception e) {
                    obj = null;
                    input.close();
                    throw e;
                }
            }
        }
        input.close();
    }

    // GETTERS AND SETTERS
    /**
     * Retorna a lista de posts.
     * @return A lista de posts na instância da classe.
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Retorna a lista de professores.
     * @return A lista de professores na instância da classe.
     */
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    // SEARCH FUNCTIONS
    /**
     * 
     * @param id long pelo qual o professor será buscado.
     * @return Retorna o professor cujo id equivale ao passado na entrada.
     */
    public Teacher getTeacher(long id) {
        Teacher aux = new Teacher(id);
        int index = teachers.indexOf(aux);
        aux = null;
        if (index == -1) {
            return null;
        }
        return teachers.get(index);
    }

    /**
     * @param cod string pela qual o veículo será buscado.
     * @return Retorna o veículo cujo código equivale ao passado na entrada.
     */
    public Vehicle getVehicle(String cod) {
        Vehicle aux = new Vehicle(cod);
        int index = vehs.indexOf(aux);
        aux = null;
        if (index == -1) {
            return null;
        }
        return vehs.get(index);
    }

    /**
     * @param year O ano do qual deseja-se buscar as regras.
     * @return As regras referentes ao ano passado no parâmetro de entrada.
     */
    public ScoreRules getActualRule(int year) {
        for (ScoreRules rule : this.rules) {
            int ruleYear = rule.getStart().getYear() + 1900;
            if (ruleYear == year) {
                return rule;
            }
        }
        return null;
    }

     /**
      * Cria uma string com todos os professores do sistema, seus scores e se serão recredenciados ou não.
      * @param year O ano ao qual deseja-se verificar os scores e quais professores serão recredenciados.
      * @return A string, no formato "Docente;Score;Recredenciado?".
      */
    public String reCredent(int year) {
        ScoreRules rule = getActualRule(year);
        if(rule == null){
            return "Regra para o ano nao encontrada";
        }

        int minScore = rule.getMinScore();
        TreeMap<String, Map<Float, String>> credents = new TreeMap<String, Map<Float, String>>();
        for (Teacher teacher : this.teachers) {
            Map<Float, String> result = new HashMap<Float, String>();
            teacher.calcScore(rule, year);
            String status = Teacher.specialCredentialCase(teacher, year);
            if (status.isEmpty()) {
                if (teacher.getScore() >= minScore) {
                    status = "Sim";
                } else {
                    status = "Não";
                }
            }
            result.put(teacher.getScore(), status);
            credents.put(teacher.getName(), result);
        }

        StringBuilder outputStr = new StringBuilder();
        outputStr.append("Docente;Pontuação;Recredenciado?\n");
        for (Map.Entry<String, Map<Float, String>> entry : credents.entrySet()) {
            outputStr.append(entry.getKey() + ";");
            entry.getValue().forEach((key, value) -> {
                String formatedScore = String.format("%.1f", key).replace(".", ",");
                outputStr.append(formatedScore + ";" + value + '\n');
            });
        }
        return outputStr.toString();
    }

    /**
     * Cria uma string com as informações de todos os posts registrados no sistema.
     * @return A string, em que cada linha tem o formato 
     * "Ano;Sigla Veículo; Veículo; Qualis; Fator de impacto; Título;Docentes".
     */
    public String listPosts() {
        StringBuilder result = new StringBuilder();
        result.append("Ano;Sigla Veículo;Veículo;Qualis;Fator de Impacto;Título;Docentes\n");
        Collections.sort(this.posts);
        this.posts.forEach(post -> result.append(post.toString() + '\n'));
        return result.toString();
    }

    /**
     * Cria uma string com quantidade de posts e média de posts para cada Qualis
     * @return A string, em que cada linha tem o formato 
     * "Qualis; Total de posts; mMdia de posts/docente".
     */
    public String estatistics() {
        /**
         * Chave: String qualis
         * Valor: HashMap cuja chave e valor são respectivamente soma de posts 
         * e média de posts por docente
         */
        TreeMap<String, HashMap<String, Float>> result = new TreeMap<String, HashMap<String, Float>>() {
            private static final long serialVersionUID = 1L;
            {
                /**
                 * Neste bloco é feita a inicialização para cada qualis
                 */
                for (String qualis : Qualify.validQualis) {
                    this.put(qualis, new HashMap<String, Float>());
                    this.get(qualis).put("sum", 0F);
                    this.get(qualis).put("post/teacher", 0F);
                }
            }
        };

        //Contagem
        for (Post post : this.posts) {
            float actualCnt = result.get(post.getQualis()).get("sum");
            float actualCntT = result.get(post.getQualis()).get("post/teacher");
            actualCnt++;
            result.get(post.getQualis()).put("sum", actualCnt);
            actualCntT += 1 / (float) post.getTeachers().size();
            result.get(post.getQualis()).put("post/teacher", actualCntT);
        }

        //Fabricação da string formatada
        StringBuilder builder = new StringBuilder();
        builder.append("Qualis;Qtd. Artigos;Média Artigos / Docente\n");
        result.forEach((key, value) -> {
            String formatedFloat = String.format("%.2f", value.get("post/teacher")).replace(".", ",");
            builder.append(String.format("%s;%.0f;%s\n", key, value.get("sum"), formatedFloat));
        });
        return builder.toString();
    }

    /**
     * Imprime as Strings geradas pelos metódos "reCredent", "listPosts" e "estatistics", cada qual
     * em um arquivo diferente.
     * @param year O inteiro que deve ser passado como ano para a "reCredent".
     */
    public void generateReports(int year, String path) throws Exception {
        String recredentString = this.reCredent(year);
        if(recredentString.compareTo("Regra para o ano nao encontrada") == 0){
            throw new Exception("Regra para o ano nao encontrada");
        }
        FileWriter credentsOut = new FileWriter(path.concat("1-recredenciamento.csv"));
        credentsOut.append(recredentString);
        credentsOut.close();

        FileWriter postsOut = new FileWriter(path.concat("2-publicacoes.csv"));
        postsOut.append(this.listPosts());
        postsOut.close();

        FileWriter statsOut = new FileWriter(path.concat("3-estatisticas.csv"));
        statsOut.append(this.estatistics());
        statsOut.close();
    }

    /**
     * Retorna o valor atual do atributo "credentYear" da instância da classe.
     * @return O valor de "credentYear".
     */
    public int getCredentYear() {
        return credentYear;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "credentYear" dessa instância da classe.
     * @param credentYear O inteiro a ser atribuído à "credentYear".
     */
    public void setCredentYear(int credentYear) {
        this.credentYear = credentYear;
    }
    
}
