package com.example.jhordan.democongresomisantla.ui;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;

/**
 * Created by Jhordan on 09/11/14.
 */
public class SendDataOtto {

    private String datos;
    private Bus eventBus;

    public SendDataOtto(Bus eventBus)
    {
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    public void meterDatos(String datos)
    {
        this.datos = datos;

        // post the updated weather on the event bus.
        eventBus.post(datos);
    }

    @Produce
    public String produceDatoUpdate()
    {
        return datos;
    }
}
