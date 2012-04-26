/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicolasgeraud.trifoto.configuration;

/**
 *
 * @author nicolasger
 */
public class UploadClientConfig {
    
    private ConnectionConfig connection;
    private String type;

    public ConnectionConfig getConnectionConfig() {
        return connection;
    }

    public void setConnectionConfig(ConnectionConfig connection) {
        this.connection = connection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
	
}
