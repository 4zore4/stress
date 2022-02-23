package com.var.stress.config;

import com.hashicorp.nomad.javasdk.NomadApiClient;
import com.hashicorp.nomad.javasdk.NomadApiConfiguration;


public class NomadConfig {

    public NomadApiClient createClient(String host){
        NomadApiConfiguration nomadApiConfiguration = new NomadApiConfiguration.Builder()
                .setAddress(host).build();
        return new NomadApiClient(nomadApiConfiguration);
    }
}
