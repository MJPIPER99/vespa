package com.yahoo.vespa.hosted.node.admin.task.util.network;

import com.google.common.net.InetAddresses;

import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * Strong type IPv4 and IPv6 with common executables for ip related commands.
 *
 * @author smorgrav
 */
public enum IPVersion {

    IPv6("ip6tables", "ip -6", "ipv6-icmp", "/128", "icmp6-port-unreachable", "ip6tables-restore"),
    IPv4("iptables", "ip", "icmp", "/32", "icmp-port-unreachable", "iptables-restore");

    IPVersion(String iptablesCmd, String ipCmd,
              String icmpProtocol, String singleHostCidr, String icmpPortUnreachable,
              String iptablesRestore) {
        this.ipCmd = ipCmd;
        this.iptablesCmd = iptablesCmd;
        this.icmpProtocol = icmpProtocol;
        this.singleHostCidr = singleHostCidr;
        this.icmpPortUnreachable = icmpPortUnreachable;
        this.iptablesRestore = iptablesRestore;
    }

    private final String iptablesCmd;
    private final String ipCmd;
    private final String icmpProtocol;
    private final String singleHostCidr;
    private final String icmpPortUnreachable;
    private final String iptablesRestore;

    public String iptablesCmd() {
        return iptablesCmd;
    }
    public String iptablesRestore() {
        return iptablesRestore;
    }
    public String ipCmd() {
        return ipCmd;
    }
    public String icmpProtocol() {
        return icmpProtocol;
    }
    public String singleHostCidr() { return singleHostCidr; }
    public String icmpPortUnreachable() { return icmpPortUnreachable; }

    public boolean match(InetAddress address) {
        return this == IPVersion.get(address);
    }

    public static IPVersion get(InetAddress address) {
        return address instanceof Inet4Address ? IPv4 : IPv6;
    }
}
