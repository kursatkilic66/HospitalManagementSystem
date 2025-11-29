package src;

import java.util.Date;

public class Visit {
    private Date date;
    private String doctorName,diagnosis,treatment;

    public Visit() {
        this.date = new Date();
        this.doctorName = null;
        this.diagnosis = null;
        this.treatment = null;
    }

    public Visit(String doctorName,String diagnosis,String treatment) {
        this.date = new Date();
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
