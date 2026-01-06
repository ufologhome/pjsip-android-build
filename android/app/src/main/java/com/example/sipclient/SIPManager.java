package com.example.sipclient;

import android.content.Context;
import android.widget.TextView;
import org.pjsip.pjsua2.*;

public class SIPManager {

    private Endpoint ep;

    public SIPManager(Context ctx) {
        ep = new Endpoint();
    }

    public void initPJSIP() {
        try {
            ep.libCreate();

            EpConfig epConfig = new EpConfig();
            ep.libInit(epConfig);

            TransportConfig udpConfig = new TransportConfig();
            udpConfig.setPort(5060);
            ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, udpConfig);

            TransportConfig tcpConfig = new TransportConfig();
            tcpConfig.setPort(5060);
            ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_TCP, tcpConfig);

            ep.libStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(String user, String pass, String domain, TextView logView) {
        try {
            AccountConfig accCfg = new AccountConfig();
            accCfg.setIdUri("sip:" + user + "@" + domain);
            accCfg.getRegConfig().setRegistrarUri("sip:" + domain);
            AuthCredInfo cred = new AuthCredInfo("digest", "*", user, 0, pass);
            accCfg.getSipConfig().getAuthCreds().add(cred);

            Account account = new Account();
            account.create(accCfg);
            logView.setText("✅ Registered: " + user);
        } catch (Exception e) {
            e.printStackTrace();
            logView.setText("❌ Registration failed: " + e.getMessage());
        }
    }
}
