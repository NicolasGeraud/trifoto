/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicolasgeraud.trifoto.configuration;

/**
 *
 * @author nicolasger
 */
public class ConnectionConfig {

    private String host;
    private int port;
    private String login;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
