package com.filmchoice.model;
import java.util.List;
import com.google.common.base.Supplier;

public class LocalProxy implements LocalInterface {
    private final Supplier<Local> supplier;
    private LocalReal localReal;


    public LocalProxy(Supplier<Local> supplier){
        this.supplier = supplier;
    }

    public void load(){
        if(localReal == null){
            this.localReal = new LocalReal(supplier.get());
        }
    }

    @Override
    public List<Double> getCoordenadas() {
        load();
        return localReal.getCoordenadas();
    }

    @Override
    public String getImagemUrl() {
        load();
        return localReal.getImagemUrl();
    }

    @Override
    public String getNomeImagem(){
        load();
        return localReal.getNomeImagem();
    }
    
}
