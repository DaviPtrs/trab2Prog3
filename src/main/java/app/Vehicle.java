package app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa um veículo no qual os Posts são realizados
 */
public class Vehicle implements Serializable{

    private static final long serialVersionUID = 5707837174044790915L;
    private String cod;
    private String name;
    private char type;
    private float influenceFactor;
    private String issn;
    private ArrayList<Qualify> qualis = new ArrayList<Qualify>();
    private ArrayList<Post> posts = new ArrayList<Post>();

    /**
     * Construtor da classe para instanciar um veículo genérico apenas com o código.
     * @param cod A string do código a ser atribuida ao atributo "cod" da instância da classe.
     */
    public Vehicle(String cod) {
        this.cod = cod;
    }

    /**
     * Construtor da classe para instanciar um veículo com os atributos "cod", "name", "type", "influenceFactor" e "issn" inicializados
     * 
     * @param cod String a ser atribuída ao atributo "cod".
     * @param name String a ser atribuída ao atributo "name".
     * @param type Caractere a ser atribuída ao atributo "type".
     * @param influenceFactor Float a ser atribuído ao atributo "influenceFactor".
     * @param issn String a ser atribuído ao atributo "issn".
     */
    public Vehicle(String cod, String name, char type, float influenceFactor, String issn) {
        this.cod = cod;
        this.name = name;
        this.type = type;
        this.influenceFactor = influenceFactor;
        this.issn = issn;
    }

    /**
     * Retorna o valor atual do atributo "cod" da instância da classe.
     * @return O código do veículo salvo no atributo "cod".
     */
    public String getCod() {
        return cod;
    }

    /**
     * Retorna o valor atual do atributo "influenceFactor" da instância da classe.
     * @return O fator de influência, salvo no atributo "influenceFactor".
     */
    public float getInfluenceFactor() {
        return influenceFactor;
    }

    /**
     * Retorna o valor atual do atributo "issn" da instância da classe.
     * @return A string atual do atributo "issn".
     */
    public String getIssn() {
        return issn;
    }

    /**
     * Retorna o valor atual do atributo "name" da instância da classe.
     * @return O nome do veículo, salvo no atributo "name".
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o valor atual do atributo "type" da instância da classe.
     * @return O tipo do veículo
     */
    public char getType() {
        return type;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "cod" da instância da classe.
     * @param cod A string a ser atribuída ao "cod".
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "influenceFactor" da instância da classe.
     * @param influenceFactor O flutuante a ser atribuído ao "influenceFactor".
     */
    public void setInfluenceFactor(float influenceFactor) {
        this.influenceFactor = influenceFactor;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "issn" da instância da classe.
     * @param issn A string a ser atribuída ao "issn".
     */
    public void setIssn(String issn){
        this.issn = issn;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "name" da instância da classe.
     * @param name A string a ser atribuída à "name".
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "type" da instância da classe.
     * @param type O caractere a ser atribuido à "type".
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * Retorna a lista de posts do atributo "posts".
     * @return A lista de posts do veículo.
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Adiciona o parâmetro de entrada na lista de posts do atributo "posts"
     * @param p O "Post" a ser adicionado na lista.
     */
    public void addPost(Post p) {
        posts.add(p);
    }

    /**
     * Adiciona um objecto Qualify à lista "qualis" da instância da classe.
     * @param q O qualis a ser adicinado na lista contida no atributo "qualis" da classe.   
     */
    public void addQualify(Qualify q) {
        qualis.add(q);
    }

    /**
     * Formata uma string com os atributos "cod", "influenceFactor", "issn", "name" e "type", e a retorna.
     * @return Uma string com informações dos atributos da classe.
     */
    @Override
    public String toString() {
        return "Vehicle [cod=" + cod + ", influenceFactor=" + influenceFactor + ", issn=" + issn + ", name=" + name
                + ", type=" + type + "]";
    }

    /**
     * Vê se o parâmetro dado como entrada é uma instância da classe Vehicle, e se possui o mesmo código
     * que essa instância da classe.
     * @return Indica se o parâmetro de entrada é igual à essa instância da classe.
     */
    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Vehicle) 
                && (((Vehicle)obj).getCod().compareTo(this.cod) == 0));
    }

    /**
     * Retorna a lista de qualis adquiridas por essa instância da classe.
     * @return A lista de qualis atuais no atributo "qualis".
     */
    public ArrayList<Qualify> getQualis() {
        return qualis;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "qualis" dessa instância da classe.
     * @param qualis Lista de qualis a ser atribuídas à classe.
     */
    public void setQualis(ArrayList<Qualify> qualis) {
        this.qualis = qualis;
    }

    /**
     * Retorna o qualis mais próximo do ano dado de entrada no parâmetro.
     * @param postingYear O ano ao qual a qualis de retorno deve ser mais próxima de.
     * @return Qualis que foi publicada mais próxima do ano de entrada.
     */
    public Qualify getAppliedQualis(int postingYear){
        Qualify result = null;
        for(Qualify q: this.qualis){
            if(result == null){
                result = q;
            }
            if((q.getYear() <= postingYear) && (q.getYear() > result.getYear())){
                result = q;
            }
        }
        return result;
    }
}