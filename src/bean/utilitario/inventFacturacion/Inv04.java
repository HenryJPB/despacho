/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.utilitario.inventFacturacion;

/**
 *
 * @author henrypb
 *
 */
public class Inv04 {

    private String C4_ORDEN;
    private String C4_CODIGO;
    private String C4_TIPO_PROD;
    private String descripcion;    // Esta campo NO pertenece a la B.D.; es utilizado para la LOV de Productos.
    private Double C4_TAML;
    private Double C4_TAMT;
    private Double C4_SEPL;
    private Double C4_SEPT;
    private Double C4_DIAML;
    private Double C4_DIAMT;
    private Integer C4_NUML;
    private Integer C4_NUMT;
    private Integer C4_SOBRL1;
    private Integer C4_SOBRL2;
    private Integer C4_SOBRT1;
    private Integer C4_SOBRT2;
    private Double C4_PESO_ITEM;
    private Integer C4_LAM;
    private Double C4_BSXTON;

    //---------------------------------------------------------------------------
    // El siguiente constructor es usado para rellenar la LOV Productos.  
    //-------------------------------------------------------------------------
    public Inv04(String C4_CODIGO, String C4_TIPO_PROD, String descripcion) {
        this.C4_CODIGO = C4_CODIGO;
        this.C4_TIPO_PROD = C4_TIPO_PROD;
        this.descripcion = descripcion;
    }

    public Inv04(String C4_ORDEN, String C4_CODIGO, String C4_TIPO_PROD, Double C4_TAML, Double C4_TAMT, Double C4_SEPL, Double C4_SEPT, Double C4_DIAML, Double C4_DIAMT, Integer C4_NUML, Integer C4_NUMT, Integer C4_SOBRL1, Integer C4_SOBRL2, Integer C4_SOBRT1, Integer C4_SOBRT2, Double C4_PESO_ITEM, Integer C4_LAM, Double C4_BSXTON) {
        this.C4_ORDEN = C4_ORDEN;
        this.C4_CODIGO = C4_CODIGO;
        this.C4_TIPO_PROD = C4_TIPO_PROD;
        this.C4_TAML = C4_TAML;
        this.C4_TAMT = C4_TAMT;
        this.C4_SEPL = C4_SEPL;
        this.C4_SEPT = C4_SEPT;
        this.C4_DIAML = C4_DIAML;
        this.C4_DIAMT = C4_DIAMT;
        this.C4_NUML = C4_NUML;
        this.C4_NUMT = C4_NUMT;
        this.C4_SOBRL1 = C4_SOBRL1;
        this.C4_SOBRL2 = C4_SOBRL2;
        this.C4_SOBRT1 = C4_SOBRT1;
        this.C4_SOBRT2 = C4_SOBRT2;
        this.C4_PESO_ITEM = C4_PESO_ITEM;
        this.C4_LAM = C4_LAM;
        this.C4_BSXTON = C4_BSXTON;
    }

    public Inv04(String C4_ORDEN, String C4_CODIGO, String C4_TIPO_PROD, String descripcion, Double C4_TAML, Double C4_TAMT, Double C4_SEPL, Double C4_SEPT, Double C4_DIAML, Double C4_DIAMT, Integer C4_NUML, Integer C4_NUMT, Integer C4_SOBRL1, Integer C4_SOBRL2, Integer C4_SOBRT1, Integer C4_SOBRT2, Double C4_PESO_ITEM, Integer C4_LAM, Double C4_BSXTON) {
        this.C4_ORDEN = C4_ORDEN;
        this.C4_CODIGO = C4_CODIGO;
        this.C4_TIPO_PROD = C4_TIPO_PROD;
        this.descripcion = descripcion;
        this.C4_TAML = C4_TAML;
        this.C4_TAMT = C4_TAMT;
        this.C4_SEPL = C4_SEPL;
        this.C4_SEPT = C4_SEPT;
        this.C4_DIAML = C4_DIAML;
        this.C4_DIAMT = C4_DIAMT;
        this.C4_NUML = C4_NUML;
        this.C4_NUMT = C4_NUMT;
        this.C4_SOBRL1 = C4_SOBRL1;
        this.C4_SOBRL2 = C4_SOBRL2;
        this.C4_SOBRT1 = C4_SOBRT1;
        this.C4_SOBRT2 = C4_SOBRT2;
        this.C4_PESO_ITEM = C4_PESO_ITEM;
        this.C4_LAM = C4_LAM;
        this.C4_BSXTON = C4_BSXTON;
    }

    public Double getC4_BSXTON() {
        return C4_BSXTON;
    }

    public void setC4_BSXTON(Double C4_BSXTON) {
        this.C4_BSXTON = C4_BSXTON;
    }

    public String getC4_CODIGO() {
        return C4_CODIGO;
    }

    public void setC4_CODIGO(String C4_CODIGO) {
        this.C4_CODIGO = C4_CODIGO;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getC4_DIAML() {
        return C4_DIAML;
    }

    public void setC4_DIAML(Double C4_DIAML) {
        this.C4_DIAML = C4_DIAML;
    }

    public Double getC4_DIAMT() {
        return C4_DIAMT;
    }

    public void setC4_DIAMT(Double C4_DIAMT) {
        this.C4_DIAMT = C4_DIAMT;
    }

    public Integer getC4_LAM() {
        return C4_LAM;
    }

    public void setC4_LAM(Integer C4_LAM) {
        this.C4_LAM = C4_LAM;
    }

    public Integer getC4_NUML() {
        return C4_NUML;
    }

    public void setC4_NUML(Integer C4_NUML) {
        this.C4_NUML = C4_NUML;
    }

    public Integer getC4_NUMT() {
        return C4_NUMT;
    }

    public void setC4_NUMT(Integer C4_NUMT) {
        this.C4_NUMT = C4_NUMT;
    }

    public String getC4_ORDEN() {
        return C4_ORDEN;
    }

    public void setC4_ORDEN(String C4_ORDEN) {
        this.C4_ORDEN = C4_ORDEN;
    }

    public Double getC4_PESO_ITEM() {
        return C4_PESO_ITEM;
    }

    public void setC4_PESO_ITEM(Double C4_PESO_ITEM) {
        this.C4_PESO_ITEM = C4_PESO_ITEM;
    }

    public Double getC4_SEPL() {
        return C4_SEPL;
    }

    public void setC4_SEPL(Double C4_SEPL) {
        this.C4_SEPL = C4_SEPL;
    }

    public Double getC4_SEPT() {
        return C4_SEPT;
    }

    public void setC4_SEPT(Double C4_SEPT) {
        this.C4_SEPT = C4_SEPT;
    }

    public Integer getC4_SOBRL1() {
        return C4_SOBRL1;
    }

    public void setC4_SOBRL1(Integer C4_SOBRL1) {
        this.C4_SOBRL1 = C4_SOBRL1;
    }

    public Integer getC4_SOBRL2() {
        return C4_SOBRL2;
    }

    public void setC4_SOBRL2(Integer C4_SOBRL2) {
        this.C4_SOBRL2 = C4_SOBRL2;
    }

    public Integer getC4_SOBRT1() {
        return C4_SOBRT1;
    }

    public void setC4_SOBRT1(Integer C4_SOBRT1) {
        this.C4_SOBRT1 = C4_SOBRT1;
    }

    public Integer getC4_SOBRT2() {
        return C4_SOBRT2;
    }

    public void setC4_SOBRT2(Integer C4_SOBRT2) {
        this.C4_SOBRT2 = C4_SOBRT2;
    }

    public Double getC4_TAML() {
        return C4_TAML;
    }

    public void setC4_TAML(Double C4_TAML) {
        this.C4_TAML = C4_TAML;
    }

    public Double getC4_TAMT() {
        return C4_TAMT;
    }

    public void setC4_TAMT(Double C4_TAMT) {
        this.C4_TAMT = C4_TAMT;
    }

    public String getC4_TIPO_PROD() {
        return C4_TIPO_PROD;
    }

    public void setC4_TIPO_PROD(String C4_TIPO_PROD) {
        this.C4_TIPO_PROD = C4_TIPO_PROD;
    }
}  // Inv04.  
