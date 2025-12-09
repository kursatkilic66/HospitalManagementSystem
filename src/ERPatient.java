package src;

// Bu sınıf SADECE Acil Servis kuyruğunda sıralama yapmak için kullanılır
public class ERPatient implements Comparable<ERPatient> {
    Patient patient;

    public ERPatient(Patient p) {
        this.patient = p;
    }

    @Override
    public int compareTo(ERPatient other) {
        // BURADA SADECE ACİLİYETE BAKIYORUZ
        // Büyük olan (10) öne geçsin istiyorsan:
        return Integer.compare(this.patient.getPriorityLevel(), other.patient.getPriorityLevel());
    }
    // ERPatient sınıfının içine bu toString metodunu ekle:
    @Override
    public String toString() {
        // İçindeki esas hasta objesinin toString'ini çağırsın
        return patient.toString() + " [Derece: " + patient.getPriorityLevel() + "]";
    }
}