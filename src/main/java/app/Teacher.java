package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Calendar;
import java.util.TimeZone;
import misc.*;

/**
 * Professor cadastrado no sistema.
 * @author Javi
 */
public class Teacher implements Serializable{
    
	private static final long serialVersionUID = 2992537304035985985L;
	private long id;
    private String name;
    private Date birthDate;
    private Date entryDate;
    private boolean isMajor;
    private float score;
    private ArrayList<Post> posts = new ArrayList<Post>();

    /**
     * Construtor que cria um "Teacher" genérico, somente com um ID, e inicia seu score em 0.
     * @param id Inteiro que identifica o professor em questão, e é guardado no atributo "id" da instância da classe.
     */

    public Teacher(long id) {
        this.id = id;
        this.score = 0;
    }

    /**
     * Construtor que cria um "teacher" com todos os seus atributos "id", "name", "birthDate", "entryDate" e "isMajor" preenchidos,
     * além de inicializar seu "score" com 0.
     * @param id Um long que será usado para inicializar o atributo "id" da classe.
     * @param name Uma string que será usado para inicializar o atributo "name" da classe.
     * @param birthDate Uma data que será usada para inicializar o atributo "birthDate" da classe.
     * @param entryDate Uma data que será usado para inicializar o atributo "entryDate" da classe.
     * @param isMajor Um booleano que será usado para inicializar o atributo "isMajor" da classe.
     */
    public Teacher(long id, String name, Date birthDate, Date entryDate, Boolean isMajor) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.entryDate = entryDate;
        this.isMajor = isMajor;
        this.score = 0;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "birthDate" da instância da classe.
     * @param birthDate Date que será atribuido à "birthDate".
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "entryDate" da instância da classe.
     * @param entryDate Date que será atribuída à "entryDate".
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "isMajor" da instância da classe.
     * @param isMajor Booleano que será atribuído à "isMajor".
     */
    public void setMajor(boolean isMajor) {
        this.isMajor = isMajor;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "name" da instância da classe.
     * @param name String que será atribuída à "name".
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calcula a idade do professor baseado com base na data/horário atual de São Paulo.
     * @return A quantidade de anos do professor no momento da chamada do metódo.
     * @param year O ano de nascimento do professor.
     */
    public int getAge(int year) {
        Calendar atual = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        atual.set(year - 1970, 0, 1);
        // atual.set(Calendar.YEAR, 2019);
        long dif = atual.getTimeInMillis() - this.birthDate.getTime();
        Calendar difDate = Calendar.getInstance();
        difDate.setTimeInMillis(dif);
        return (difDate.get(Calendar.YEAR));
    }

    /**
     * Retorna o tempo de ensino do professor com base na data/horário de São Paulo.
     * @param year O ano no qual o professor foi cadastrado no sistema.
     * @return O tempo (em anos) que o professor passou cadastrado no sistema
     */
    public int getTeachingTime(int year){
        Calendar atual = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        atual.set(year - 1970, 0, 1);
        // atual.set(Calendar.YEAR, 2019);
        long dif = atual.getTimeInMillis() - this.entryDate.getTime();
        Calendar difDate = Calendar.getInstance();
        difDate.setTimeInMillis(dif);
        return (difDate.get(Calendar.YEAR));
    }

    /**
     * Retorna o valor atual do atributo "birthDate" da instância da classe.
     * @return A data de nascimento atual no atributo "birthDate"
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Retorna o valor atual do atributo "entryDate" da instância da classe.
     * @return A data de entrada atual no atributo "entryDate".
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * Retorna o valor atual do atributo "id" da instância da classe.
     * @return O identificador atual no atributo "id".
     */
    public long getId() {
        return id;
    }

    /**
     * Retorna o valor atual do atributo "name" da instância da classe.
     * @return O nome atual no atributo "name".
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o valor atual do atributo "isMajor" da instância da classe.
     * @return O valor atual no atributo "isMajor".
     */
    public boolean isMajor() {
        return this.isMajor;
    }

    /**
     * Retorna a lista de "Post" do atributo "posts" da instância da classe.
     * @return A lista de posts atual no atributo "posts".
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Adiciona o "Post" no parâmetro de entrada à lista de Post do atributo "posts" da instância da classe.
     * @param p o Post a ser adicionado na lista de posts do professor.
     */
    public void addPost(Post p) {
        posts.add(p);
    }


    /**
     * Formata os atributos "birthDate", "entryDate", "id", "isMajor" e "name" para uma string e a retorna.
     * @return Atributos (únicos) da instância da classe numa string.
     */
    @Override
    public String toString() {
        return "Teacher [birthDate=" + Utils.dateToString(birthDate) + ", entryDate=" + Utils.dateToString(entryDate) 
                + ", id=" + id + ", isMajor=" + isMajor + ", name=" + name + "]";
    }

    /**
     * Retorna um booleano que indica se o objeto do parâmetro de entrada é uma instância da classe Teacher, e se seu id é igual
     * ao id desta instância da classe. (essencialmente, se objeto passado como entrada é o mesmo que essa instância da classe teacher).
     * @return Indicador de igualdade entre o objeto do parâmetro de entrada e essa instância da classe.
     */
    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Teacher) 
                && (((Teacher)obj).getId() == this.id));
    }

    /**
     * Retorna uma string que diz as credencias especiais do professor, caso existam, e uma string vazia caso contrário
     * @param t O professor ao qual deseja-se verificar as credencias.
     * @param year O ano que será usado como base para se fazer a verificação.
     * @return As credenciais do professor dado na entrada, com base no ano.
     */
    public static String specialCredentialCase(Teacher t, int year){
        String credentialCase = "";
        if(t.isMajor()){
            credentialCase = "Coordenador";
        }else if(t.getTeachingTime(year) < 3){
            credentialCase = "PPJ";
        }else if(t.getAge(year) >= 60){
            credentialCase = "PPS";
        }
        return credentialCase;
    }

    
    /**
     * Retorna o valor atual do atributo "score" da instância da classe.
     * @return O score do professor, salvo no atributo "score".
     */
    public float getScore() {
        return score;
    }
    

    /**
     * Atibui o score dessa instância da classe com base nas regras dadas de entrada, e o ano limitante.
     * @param rule As regras pelas quais deve-se pontuar.
     * @param year O ano que compreende o limite superior ao qual os critérios de pontuação estarão limitados.
     */
    public void calcScore(ScoreRules rule, int year){
        float score = 0;
        float factor = rule.getPeriodicMulti();
        Map<String, Integer> qualisDict = rule.getQualis();
        int minYear = year - rule.getYearsCnt();
        for(Post post: this.posts){
            Vehicle veh = post.getVehicle();
            Qualify quali = veh.getAppliedQualis(post.getYear());
            post.setQualis(quali.getQualis());
            if((post.getYear() >= minYear) && (post.getYear() < year)){
                int scoreAcc = qualisDict.get(quali.getQualis());
                if(post instanceof Periodic){
                    scoreAcc *= factor;
                }
                score += scoreAcc;
            }
        }
        this.score = score;
    }

}