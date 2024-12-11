package demo.demo.Module;
public class HotelDepartment {
    private int SNO;
    private Integer Hotelno;
    private int Departmentno;

    public HotelDepartment(int SNO, Integer hotelno, int   departmentno) {
        this.SNO = SNO;
        Hotelno = hotelno;
        Departmentno = departmentno;
    }

    public int getSNO() {
        return SNO;
    }

    public void setSNO(int SNO) {
        this.SNO = SNO;
    }

    public Integer getHotelno() {
        return Hotelno;
    }

    public void setHotelno(Integer hotelno) {
        Hotelno = hotelno;
    }

    public int getDepartmentno() {
        return Departmentno;
    }

    public void setDepartmentno(int  departmentno) {
        Departmentno = departmentno;
    }
// Getters and Setters
}
