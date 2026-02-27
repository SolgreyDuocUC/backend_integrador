package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accesos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accesos implements Serializable {

    @EmbeddedId
    private AccesosId id;

    @Column(name = "Menu_01")
    private Boolean menu01;

    @Column(name = "Menu_02")
    private Boolean menu02;

    @Column(name = "Menu_03")
    private Boolean menu03;

    @Column(name = "Menu_04")
    private Boolean menu04;

    @Column(name = "Menu_05")
    private Boolean menu05;

    @Column(name = "Menu_06")
    private Boolean menu06;

    @Column(name = "Menu_07")
    private Boolean menu07;

    @Column(name = "Menu_08")
    private Boolean menu08;

    @Column(name = "Menu_09")
    private Boolean menu09;

    @Column(name = "Menu_10")
    private Boolean menu10;

    @Column(name = "M01_Op01")
    private Boolean m01Op01;

    @Column(name = "M01_Op02")
    private Boolean m01Op02;

    @Column(name = "M01_Op03")
    private Boolean m01Op03;

    @Column(name = "M01_Op04")
    private Boolean m01Op04;

    @Column(name = "M01_Op05")
    private Boolean m01Op05;

    @Column(name = "M01_Op06")
    private Boolean m01Op06;

    @Column(name = "M01_Op07")
    private Boolean m01Op07;

    @Column(name = "M01_Op08")
    private Boolean m01Op08;

    @Column(name = "M01_Op09")
    private Boolean m01Op09;

    @Column(name = "M01_Op10")
    private Boolean m01Op10;

    @Column(name = "M01_Op11")
    private Boolean m01Op11;

    @Column(name = "M01_Op12")
    private Boolean m01Op12;

    @Column(name = "M01_Op13")
    private Boolean m01Op13;

    @Column(name = "M01_Op14")
    private Boolean m01Op14;

    @Column(name = "M01_Op15")
    private Boolean m01Op15;

    @Column(name = "M01_Op16")
    private Boolean m01Op16;

    @Column(name = "M01_Op17")
    private Boolean m01Op17;

    @Column(name = "M01_Op18")
    private Boolean m01Op18;

    @Column(name = "M01_Op19")
    private Boolean m01Op19;

    @Column(name = "M01_Op20")
    private Boolean m01Op20;

    @Column(name = "M02_Op01")
    private Boolean m02Op01;

    @Column(name = "M02_Op02")
    private Boolean m02Op02;

    @Column(name = "M02_Op03")
    private Boolean m02Op03;

    @Column(name = "M02_Op04")
    private Boolean m02Op04;

    @Column(name = "M02_Op05")
    private Boolean m02Op05;

    @Column(name = "M02_Op06")
    private Boolean m02Op06;

    @Column(name = "M02_Op07")
    private Boolean m02Op07;

    @Column(name = "M02_Op08")
    private Boolean m02Op08;

    @Column(name = "M02_Op09")
    private Boolean m02Op09;

    @Column(name = "M02_Op10")
    private Boolean m02Op10;

    @Column(name = "M02_Op11")
    private Boolean m02Op11;

    @Column(name = "M02_Op12")
    private Boolean m02Op12;

    @Column(name = "M02_Op13")
    private Boolean m02Op13;

    @Column(name = "M02_Op14")
    private Boolean m02Op14;

    @Column(name = "M02_Op15")
    private Boolean m02Op15;

    @Column(name = "M02_Op16")
    private Boolean m02Op16;

    @Column(name = "M02_Op17")
    private Boolean m02Op17;

    @Column(name = "M02_Op18")
    private Boolean m02Op18;

    @Column(name = "M02_Op19")
    private Boolean m02Op19;

    @Column(name = "M02_Op20")
    private Boolean m02Op20;

    @Column(name = "M03_Op01")
    private Boolean m03Op01;

    @Column(name = "M03_Op02")
    private Boolean m03Op02;

    @Column(name = "M03_Op03")
    private Boolean m03Op03;

    @Column(name = "M03_Op04")
    private Boolean m03Op04;

    @Column(name = "M03_Op05")
    private Boolean m03Op05;

    @Column(name = "M03_Op06")
    private Boolean m03Op06;

    @Column(name = "M03_Op07")
    private Boolean m03Op07;

    @Column(name = "M03_Op08")
    private Boolean m03Op08;

    @Column(name = "M03_Op09")
    private Boolean m03Op09;

    @Column(name = "M03_Op10")
    private Boolean m03Op10;

    @Column(name = "M03_Op11")
    private Boolean m03Op11;

    @Column(name = "M03_Op12")
    private Boolean m03Op12;

    @Column(name = "M03_Op13")
    private Boolean m03Op13;

    @Column(name = "M03_Op14")
    private Boolean m03Op14;

    @Column(name = "M03_Op15")
    private Boolean m03Op15;

    @Column(name = "M03_Op16")
    private Boolean m03Op16;

    @Column(name = "M03_Op17")
    private Boolean m03Op17;

    @Column(name = "M03_Op18")
    private Boolean m03Op18;

    @Column(name = "M03_Op19")
    private Boolean m03Op19;

    @Column(name = "M03_Op20")
    private Boolean m03Op20;

    @Column(name = "M04_Op01")
    private Boolean m04Op01;

    @Column(name = "M04_Op02")
    private Boolean m04Op02;

    @Column(name = "M04_Op03")
    private Boolean m04Op03;

    @Column(name = "M04_Op04")
    private Boolean m04Op04;

    @Column(name = "M04_Op05")
    private Boolean m04Op05;

    @Column(name = "M04_Op06")
    private Boolean m04Op06;

    @Column(name = "M04_Op07")
    private Boolean m04Op07;

    @Column(name = "M04_Op08")
    private Boolean m04Op08;

    @Column(name = "M04_Op09")
    private Boolean m04Op09;

    @Column(name = "M04_Op10")
    private Boolean m04Op10;

    @Column(name = "M04_Op11")
    private Boolean m04Op11;

    @Column(name = "M04_Op12")
    private Boolean m04Op12;

    @Column(name = "M04_Op13")
    private Boolean m04Op13;

    @Column(name = "M04_Op14")
    private Boolean m04Op14;

    @Column(name = "M04_Op15")
    private Boolean m04Op15;

    @Column(name = "M04_Op16")
    private Boolean m04Op16;

    @Column(name = "M04_Op17")
    private Boolean m04Op17;

    @Column(name = "M04_Op18")
    private Boolean m04Op18;

    @Column(name = "M04_Op19")
    private Boolean m04Op19;

    @Column(name = "M04_Op20")
    private Boolean m04Op20;

    @Column(name = "M05_Op01")
    private Boolean m05Op01;

    @Column(name = "M05_Op02")
    private Boolean m05Op02;

    @Column(name = "M05_Op03")
    private Boolean m05Op03;

    @Column(name = "M05_Op04")
    private Boolean m05Op04;

    @Column(name = "M05_Op05")
    private Boolean m05Op05;

    @Column(name = "M05_Op06")
    private Boolean m05Op06;

    @Column(name = "M05_Op07")
    private Boolean m05Op07;

    @Column(name = "M05_Op08")
    private Boolean m05Op08;

    @Column(name = "M05_Op09")
    private Boolean m05Op09;

    @Column(name = "M05_Op10")
    private Boolean m05Op10;

    @Column(name = "M05_Op11")
    private Boolean m05Op11;

    @Column(name = "M05_Op12")
    private Boolean m05Op12;

    @Column(name = "M05_Op13")
    private Boolean m05Op13;

    @Column(name = "M05_Op14")
    private Boolean m05Op14;

    @Column(name = "M05_Op15")
    private Boolean m05Op15;

    @Column(name = "M05_Op16")
    private Boolean m05Op16;

    @Column(name = "M05_Op17")
    private Boolean m05Op17;

    @Column(name = "M05_Op18")
    private Boolean m05Op18;

    @Column(name = "M05_Op19")
    private Boolean m05Op19;

    @Column(name = "M05_Op20")
    private Boolean m05Op20;

    @Column(name = "M06_Op01")
    private Boolean m06Op01;

    @Column(name = "M06_Op02")
    private Boolean m06Op02;

    @Column(name = "M06_Op03")
    private Boolean m06Op03;

    @Column(name = "M06_Op04")
    private Boolean m06Op04;

    @Column(name = "M06_Op05")
    private Boolean m06Op05;

    @Column(name = "M06_Op06")
    private Boolean m06Op06;

    @Column(name = "M06_Op07")
    private Boolean m06Op07;

    @Column(name = "M06_Op08")
    private Boolean m06Op08;

    @Column(name = "M06_Op09")
    private Boolean m06Op09;

    @Column(name = "M06_Op10")
    private Boolean m06Op10;

    @Column(name = "M06_Op11")
    private Boolean m06Op11;

    @Column(name = "M06_Op12")
    private Boolean m06Op12;

    @Column(name = "M06_Op13")
    private Boolean m06Op13;

    @Column(name = "M06_Op14")
    private Boolean m06Op14;

    @Column(name = "M06_Op15")
    private Boolean m06Op15;

    @Column(name = "M06_Op16")
    private Boolean m06Op16;

    @Column(name = "M06_Op17")
    private Boolean m06Op17;

    @Column(name = "M06_Op18")
    private Boolean m06Op18;

    @Column(name = "M06_Op19")
    private Boolean m06Op19;

    @Column(name = "M06_Op20")
    private Boolean m06Op20;

    @Column(name = "M07_Op01")
    private Boolean m07Op01;

    @Column(name = "M07_Op02")
    private Boolean m07Op02;

    @Column(name = "M07_Op03")
    private Boolean m07Op03;

    @Column(name = "M07_Op04")
    private Boolean m07Op04;

    @Column(name = "M07_Op05")
    private Boolean m07Op05;

    @Column(name = "M07_Op06")
    private Boolean m07Op06;

    @Column(name = "M07_Op07")
    private Boolean m07Op07;

    @Column(name = "M07_Op08")
    private Boolean m07Op08;

    @Column(name = "M07_Op09")
    private Boolean m07Op09;

    @Column(name = "M07_Op10")
    private Boolean m07Op10;

    @Column(name = "M07_Op11")
    private Boolean m07Op11;

    @Column(name = "M07_Op12")
    private Boolean m07Op12;

    @Column(name = "M07_Op13")
    private Boolean m07Op13;

    @Column(name = "M07_Op14")
    private Boolean m07Op14;

    @Column(name = "M07_Op15")
    private Boolean m07Op15;

    @Column(name = "M07_Op16")
    private Boolean m07Op16;

    @Column(name = "M07_Op17")
    private Boolean m07Op17;

    @Column(name = "M07_Op18")
    private Boolean m07Op18;

    @Column(name = "M07_Op19")
    private Boolean m07Op19;

    @Column(name = "M07_Op20")
    private Boolean m07Op20;

    @Column(name = "M08_Op01")
    private Boolean m08Op01;

    @Column(name = "M08_Op02")
    private Boolean m08Op02;

    @Column(name = "M08_Op03")
    private Boolean m08Op03;

    @Column(name = "M08_Op04")
    private Boolean m08Op04;

    @Column(name = "M08_Op05")
    private Boolean m08Op05;

    @Column(name = "M08_Op06")
    private Boolean m08Op06;

    @Column(name = "M08_Op07")
    private Boolean m08Op07;

    @Column(name = "M08_Op08")
    private Boolean m08Op08;

    @Column(name = "M08_Op09")
    private Boolean m08Op09;

    @Column(name = "M08_Op10")
    private Boolean m08Op10;

    @Column(name = "M08_Op11")
    private Boolean m08Op11;

    @Column(name = "M08_Op12")
    private Boolean m08Op12;

    @Column(name = "M08_Op13")
    private Boolean m08Op13;

    @Column(name = "M08_Op14")
    private Boolean m08Op14;

    @Column(name = "M08_Op15")
    private Boolean m08Op15;

    @Column(name = "M08_Op16")
    private Boolean m08Op16;

    @Column(name = "M08_Op17")
    private Boolean m08Op17;

    @Column(name = "M08_Op18")
    private Boolean m08Op18;

    @Column(name = "M08_Op19")
    private Boolean m08Op19;

    @Column(name = "M08_Op20")
    private Boolean m08Op20;

    @Column(name = "M09_Op01")
    private Boolean m09Op01;

    @Column(name = "M09_Op02")
    private Boolean m09Op02;

    @Column(name = "M09_Op03")
    private Boolean m09Op03;

    @Column(name = "M09_Op04")
    private Boolean m09Op04;

    @Column(name = "M09_Op05")
    private Boolean m09Op05;

    @Column(name = "M09_Op06")
    private Boolean m09Op06;

    @Column(name = "M09_Op07")
    private Boolean m09Op07;

    @Column(name = "M09_Op08")
    private Boolean m09Op08;

    @Column(name = "M09_Op09")
    private Boolean m09Op09;

    @Column(name = "M09_Op10")
    private Boolean m09Op10;

    @Column(name = "M09_Op11")
    private Boolean m09Op11;

    @Column(name = "M09_Op12")
    private Boolean m09Op12;

    @Column(name = "M09_Op13")
    private Boolean m09Op13;

    @Column(name = "M09_Op14")
    private Boolean m09Op14;

    @Column(name = "M09_Op15")
    private Boolean m09Op15;

    @Column(name = "M09_Op16")
    private Boolean m09Op16;

    @Column(name = "M09_Op17")
    private Boolean m09Op17;

    @Column(name = "M09_Op18")
    private Boolean m09Op18;

    @Column(name = "M09_Op19")
    private Boolean m09Op19;

    @Column(name = "M09_Op20")
    private Boolean m09Op20;

    @Column(name = "M10_Op01")
    private Boolean m10Op01;

    @Column(name = "M10_Op02")
    private Boolean m10Op02;

    @Column(name = "M10_Op03")
    private Boolean m10Op03;

    @Column(name = "M10_Op04")
    private Boolean m10Op04;

    @Column(name = "M10_Op05")
    private Boolean m10Op05;

    @Column(name = "M10_Op06")
    private Boolean m10Op06;

    @Column(name = "M10_Op07")
    private Boolean m10Op07;

    @Column(name = "M10_Op08")
    private Boolean m10Op08;

    @Column(name = "M10_Op09")
    private Boolean m10Op09;

    @Column(name = "M10_Op10")
    private Boolean m10Op10;

    @Column(name = "M10_Op11")
    private Boolean m10Op11;

    @Column(name = "M10_Op12")
    private Boolean m10Op12;

    @Column(name = "M10_Op13")
    private Boolean m10Op13;

    @Column(name = "M10_Op14")
    private Boolean m10Op14;

    @Column(name = "M10_Op15")
    private Boolean m10Op15;

    @Column(name = "M10_Op16")
    private Boolean m10Op16;

    @Column(name = "M10_Op17")
    private Boolean m10Op17;

    @Column(name = "M10_Op18")
    private Boolean m10Op18;

    @Column(name = "M10_Op19")
    private Boolean m10Op19;

    @Column(name = "M10_Op20")
    private Boolean m10Op20;

}
