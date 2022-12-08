package com.example.plannerapplication;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarioUtils {

    public static LocalDate dataSelecionada;

    public static String dataFormatada(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    public static String mesDoAnoFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public static ArrayList<LocalDate> diasDoMesArray() {
        ArrayList <LocalDate> diasDoMesArray = new ArrayList<>();

        YearMonth mesDoAno = YearMonth.from(dataSelecionada);
        int diasDoMes = mesDoAno.lengthOfMonth();

        LocalDate mesAnterior = dataSelecionada.minusMonths(1);
        LocalDate mesSeguinte = dataSelecionada.plusMonths(1);

        YearMonth AntMesDoAno = YearMonth.from(mesAnterior);
        int AntdiasDoMes = AntMesDoAno.lengthOfMonth();

        LocalDate primeiroDoMes = CalendarioUtils.dataSelecionada.withDayOfMonth(1);
        int diaDaSemana = primeiroDoMes.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++ ){
            if(i<= diaDaSemana){
                diasDoMesArray.add(LocalDate.of(mesAnterior.getYear(),mesAnterior.getMonth(), AntdiasDoMes + i - diaDaSemana));
            } else if (i > diasDoMes + diaDaSemana){
                diasDoMesArray.add(LocalDate.of(mesSeguinte.getYear(),mesSeguinte.getMonth(),i - diaDaSemana - diasDoMes));
            } else {
                diasDoMesArray.add(LocalDate.of(dataSelecionada.getYear(),dataSelecionada.getMonth(),i - diaDaSemana));
            }
        }
        return diasDoMesArray;
    }

    public static ArrayList<LocalDate> diasDaSemanaArray(LocalDate dataSelecionada) {
        ArrayList <LocalDate> dias = new ArrayList<>();
        LocalDate atual = domingoParaData(dataSelecionada);
        LocalDate fimData = atual.plusWeeks(1);

        while (atual.isBefore(fimData)){
            dias.add(atual);
            atual = atual.plusDays(1);
        }
        return dias;
    }

    private static LocalDate domingoParaData(LocalDate atual) {
        LocalDate semanaPassada = atual.minusWeeks(1);

        while (atual.isAfter(semanaPassada)){
            if (atual.getDayOfWeek() == DayOfWeek.SUNDAY)
                return atual;

            atual = atual.minusDays(1);
        }
        return null;
    }

}
