package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(8);
        assertEquals(8, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLaittaaLiikaa() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.otaVarastosta(12), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaLiianVahan() {
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisaaLiianVahan() {
        varasto.lisaaVarastoon(-1);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
    
    @Test
    public void pieniTilavuusOnAinaNolla() {
        
        Varasto varastob = new Varasto(0);
        Varasto varastoc = new Varasto(-1);
        assertEquals(0, varastob.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varastoc.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiAlkusaldollaOnnistuu() {
        
        Varasto varastob = new Varasto(10, 5);
        Varasto varastoc = new Varasto(10, 15);
        Varasto varastod = new Varasto(-1, -1);
        assertEquals(5, varastob.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(10, varastoc.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varastod.getSaldo(), vertailuTarkkuus);
        assertEquals(1, varastod.getTilavuus(), vertailuTarkkuus);
    }
    
}