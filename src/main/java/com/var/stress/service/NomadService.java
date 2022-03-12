package com.var.stress.service;

import com.hashicorp.nomad.javasdk.NomadException;

import java.io.IOException;

public interface NomadService {
    public void run() throws NomadException, IOException;
    public void stop();
}
