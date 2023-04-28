/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ZT
 */
public class RequestModel extends VmModel {
    
    public RequestModel(String vmid, int mips, long size, int ram, long bw, int pesNumber, String vmm) {
        super(vmid, mips, size, ram, bw, pesNumber, vmm);
    }
    
}
