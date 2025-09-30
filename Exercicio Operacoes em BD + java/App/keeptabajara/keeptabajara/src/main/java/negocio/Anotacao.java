package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public class Anotacao {
    private int id;
    private String titulo;
    private String texto;
    private String cor;
    private LocalDateTime dataHora;
    private byte[] foto;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTexto() {
        return texto;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public String getCor() {
        return cor;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    @Override
    public String toString() {
        return "Anotacao [id=" + id + ", titulo=" + titulo + ", texto=" + texto +", cor=" + cor + ", dataHora=" + dataHora +", foto="+ foto +"]";
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setFoto(String diretorio) throws FileNotFoundException, IOException {        
        File f = new File(diretorio);
        FileInputStream fileInputStream = new FileInputStream(f);
        this.foto = fileInputStream.readAllBytes();
        fileInputStream.close();
    }

    
    
    
}
