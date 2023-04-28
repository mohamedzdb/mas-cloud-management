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
public class VmModel {
    int vmid;
    String vmidstr;
    int mips ;
    long size ; 
    int ram; 
    long bw;
    int pesNumber;
    String vmm ; 
    String status;

    public VmModel(int vmid, int mips, long size, int ram, long bw, int pesNumber, String vmm, String status) {
        this.vmid = vmid;
        this.mips = mips;
        this.size = size;
        this.ram = ram;
        this.bw = bw;
        this.pesNumber = pesNumber;
        this.vmm = vmm;
        this.status = status;
    }

    public VmModel(String vmidstr, int mips, long size, int ram, long bw, int pesNumber, String vmm, String status) {
        this.vmidstr = vmidstr;
        this.mips = mips;
        this.size = size;
        this.ram = ram;
        this.bw = bw;
        this.pesNumber = pesNumber;
        this.vmm = vmm;
        this.status = status;
    }

    
    

    public VmModel(String vmidstr, int mips, long size, int ram, long bw, int pesNumber, String vmm) {
        this.vmidstr = vmidstr;
        this.mips = mips;
        this.size = size;
        this.ram = ram;
        this.bw = bw;
        this.pesNumber = pesNumber;
        this.vmm = vmm;
    }

    
    
    public int getVmid() {
        return vmid;
    }

    public void setVmid(int vmid) {
        this.vmid = vmid;
    }

    public String getVmidstr() {
        return vmidstr;
    }

    public void setVmidstr(String vmidstr) {
        this.vmidstr = vmidstr;
    }
    
    public int getMips() {
        return mips;
    }

    public void setMips(int mips) {
        this.mips = mips;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public long getBw() {
        return bw;
    }

    public void setBw(long bw) {
        this.bw = bw;
    }

    public int getPesNumber() {
        return pesNumber;
    }

    public void setPesNumber(int pesNumber) {
        this.pesNumber = pesNumber;
    }

    public String getVmm() {
        return vmm;
    }

    public void setVmm(String vmm) {
        this.vmm = vmm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
