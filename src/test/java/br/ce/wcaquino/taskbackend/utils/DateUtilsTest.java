package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueParaDatasFuturas(){
        LocalDate localDate = LocalDate.of(2030, 01, 01);
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(localDate));
    }

    @Test
    public void deveRetornarFalseParaDatasPassadas(){
        LocalDate localDate = LocalDate.of(2010, 01, 01);
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(localDate));
    }

    @Test
    public void deveRetornarTrueParaDataAtual(){
        LocalDate localDate = LocalDate.now();
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(localDate));
    }
}
