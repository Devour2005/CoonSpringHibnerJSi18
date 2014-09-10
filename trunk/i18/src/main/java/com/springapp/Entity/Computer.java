package com.springapp.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "computers")
@NamedQueries({
        @NamedQuery(name = "Comp.getAllUsers", query = "SELECT c FROM Computer c")})
public class Computer implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "comp_id")
    private Integer compId;

    @Column(name = "pcname")
    private String pcName;

    @Column(name = "monitor")
    private String monitor;

    @Column(name = "keyboard")
    private String keyboard;

    @Column(name = "mouse")
    private String mouse;

    @Column(name = "cpu")
    private String cpu;

    public Computer() {
    }

    @SuppressWarnings("unchecked")
    @ManyToMany(mappedBy = "computers",
            fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    private Set<User> user = new LinkedHashSet<>();

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }


    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compid) {
        this.compId = compid;
    }


    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }


    @Override
    public boolean equals(Object ref) {
        if (ref == null || getClass() != ref.getClass()) {
            return false;
        }
        Computer that = (Computer) ref;
        if (compId != that.compId) return false;
        if (pcName != null ? !pcName.equals(that.pcName) : that.pcName != null) return false;
        if (monitor != null ? !monitor.equals(that.monitor) : that.monitor != null) return false;
        if (keyboard != null ? !keyboard.equals(that.keyboard) : that.keyboard != null) return false;
        if (mouse != null ? !mouse.equals(that.mouse) : that.mouse != null) return false;
        if (cpu != null ? !cpu.equals(that.cpu) : that.cpu != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(compId, pcName, monitor, keyboard, mouse, cpu);
    }

    @Override
    public String toString() {
        return new StringBuilder("Computer {Id= '")
                .append(compId)
                .append("\'")
                .append(", pcName= '")
                .append(pcName)
                .append("\'")
                .append(", monitor= '")
                .append(monitor)
                .append("\'")
                .append(", keyboard= '")
                .append(keyboard)
                .append("\'")
                .append(", mouse= '")
                .append(mouse)
                .append(", cpu= '")
                .append(cpu)
                .append("\'")
                .append("'}\r\n").toString();
    }
}
