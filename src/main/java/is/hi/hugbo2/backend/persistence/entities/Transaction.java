package is.hi.hugbo2.backend.persistence.entities;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "transaction")
public class Transaction {

    // Declare that this attribute is the id and auto generate
    @Id
    @SequenceGenerator(name = "transactionGenerator", sequenceName = "TRANSACTION_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionGenerator")
    @Column(name = "transactionId", updatable = false, nullable = false)
    private Long id;
    private Long splitId;

    private Boolean confirmed;
    private Boolean ignored;

    // JPA relationship to account
    // @ManyToOne(fetch = FetchType.LAZY)
    private Long accountId;

    private Double amount;
    private String descr;

    @Embedded
    private List<String> splitInfo;

    //Auto genarate the date of creation
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    /**
     * Empty constructor
     */
    public Transaction() {
    }

    /**
     * default  constructor
     * @param accountId
     * @param amount
     */
    public Transaction(Long accountId, Double amount, String descr) {
        this.accountId = accountId;
        this.amount = amount;
        this.descr = descr;

    }

    /**
     * Getters n setters
     * @return
     */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSplitId() {
        return splitId;
    }
    public void setSplitId(Long splitId) {
        this.splitId = splitId;
    }
    public Boolean getConfirmed() {
        return confirmed;
    }
    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
    public Boolean getIgnored() {
        return ignored;
    }
    public void setIgnored(Boolean ignored) {
        this.ignored = ignored;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public List<String> getSplitInfo() {return splitInfo;}
    public void setSplitInfo(List<String> splitInfo) {this.splitInfo = splitInfo;}
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {this.accountId = accountId;}
    public Date getDate() {return date;}
}