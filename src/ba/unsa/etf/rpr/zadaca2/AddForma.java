package ba.unsa.etf.rpr.zadaca2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.commons.validator.routines.EmailValidator;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;


public class AddForma {
    public TextField naslovField;
    public TextField autorField;
    public TextField ISBNField;
    public DatePicker datumIzdanjaField;

    public SimpleStringProperty naslovProperty;
    public SimpleStringProperty autorProperty;
    public SimpleStringProperty ISBNProperty;


    public ValidationSupport validation;
    public ComboBox odsjekField;
    public ComboBox godinaStudijaField;
    public ComboBox ciklusStudijaField;
    public ComboBox redovanSamofinansirajuciField;
    public ComboBox posebnaKategorijaField;
    public Button potvrdiBtn;

    public AddForma() {
        naslovProperty = new SimpleStringProperty("");
        autorProperty = new SimpleStringProperty("");
        ISBNProperty = new SimpleStringProperty("");

        validation = new ValidationSupport();
        //validation.registerValidator(imeField, Validator.createEmptyValidator("Ime ne moze biti prazno!"));

    }

    @FXML
    public void initialize() {
        naslovField.textProperty().bindBidirectional(naslovProperty);
        autorField.textProperty().bindBidirectional(autorProperty);
        ISBNField.textProperty().bindBidirectional(ISBNProperty);
        datumIzdanjaField.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datumIzdanjaField.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        dodajListenere();
    }

    private boolean validnost(String n) {
        int i = 0;
        char c;

        if (n == null) {
            return false;
        }

        if (n.length() > 20 || n.length() <= 0) {
            return false;
        }

        for (i = 0; i < n.length(); i++)  //Check for `Firstname`
        {
            c = n.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    private boolean validnost2(String n) {
        if (n == null) {
            return false;
        }

        if (n.length() > 20 || n.length() <= 0) {
            return false;
        }
        return true;
    }

    private boolean ispravanIndeks(String s) {
        if (s.length() != 5 && s.length() > 0 && s.charAt(0) != 1) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean cifraCheck(String broj) {
        boolean validno = false;

        char[] charovi = broj.toCharArray();
        for (int i = 0; i < charovi.length; i++) {
            validno = false;
            if (((charovi[i] >= '0') && (charovi[i] <= '9'))) {
                validno = true;
            }
        }
        return validno;
    }

    private boolean ispravanBroj(String n) {
        int i = 0;

        if (n.length() < 9 || n.length() > 10 || n.charAt(0) != '0') {
            return false;
        }

        for (i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) {
                return false;
            }
        }

        return true;
    }

    private boolean ispravanJMBG(String s) {
        // Nadjedno na netu
        List<Integer> lista = new ArrayList<Integer>();
        if (cifraCheck(s)) {
            for (char ch : s.toCharArray()) {
                lista.add(Integer.valueOf(String.valueOf(ch)));
            }

            if (lista.size() != 13) {
                return false;
            } else {
                int eval = 0;
                for (int i = 0; i < 6; i++) {
                    eval += (7 - i) * (lista.get(i) + lista.get(i + 6));
                }
                return lista.get(12) == 11 - eval % 11;
            }
        } else {
            return false;
        }
    }


    private boolean ispravanDatum(String s) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);

        try {
            format.parse(s);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private void dodajListenere() {
        //Listener za imeField
        naslovField.textProperty().addListener((observableValue, o, n) -> {
            if (validnost(n)) {
                naslovField.getStyleClass().removeAll("poljeNijeIspravno");
                naslovField.getStyleClass().add("poljeIspravno");
            } else {
                naslovField.getStyleClass().removeAll("poljeIspravno");
                naslovField.getStyleClass().add("poljeNijeIspravno");
            }
        });

        naslovField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!naslovField.isFocused()) {
                    //Kombinacija empty string validatora i
                    // predicate validatora koji poziva metodu validnost
                    Validator validator = Validator.combine(
                            Validator.createEmptyValidator("Ime ne može biti prazno!"),
                            Validator.createPredicateValidator((Predicate<String>) s -> validnost(s),
                                    "Neispravno ime!")
                    );
                    validation.registerValidator(naslovField, validator);
                } else {
                    // Hack sa controlsFX bitbucketa (u sustini registrira prazan validator ako komponenta nije
                    // fokusirana
                    validation.registerValidator(naslovField, false, (Control c, String s) ->
                            ValidationResult.fromErrorIf(c, "", false));
                }
            }
        });

        //Listener za prezimeField
        autorField.textProperty().addListener((observableValue, o, n) -> {
            if (validnost(n)) {
                autorField.getStyleClass().removeAll("poljeNijeIspravno");
                autorField.getStyleClass().add("poljeIspravno");
            } else {
                autorField.getStyleClass().removeAll("poljeIspravno");
                autorField.getStyleClass().add("poljeNijeIspravno");
            }
        });
        autorField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!autorField.isFocused()) {
                    //Kombinacija empty string validatora i
                    // predicate validatora koji poziva metodu validnost
                    Validator validator = Validator.combine(
                            Validator.createEmptyValidator("Prezime ne može biti prazno!"),
                            Validator.createPredicateValidator((Predicate<String>) s -> validnost(s),
                                    "Neispravno prezime!")
                    );
                    validation.registerValidator(autorField, validator);
                } else {
                    // Hack sa controlsFX bitbucketa (u sustini registrira prazan validator ako komponenta nije
                    // fokusirana
                    validation.registerValidator(autorField, false, (Control c, String s) ->
                            ValidationResult.fromErrorIf(c, "", false));
                }
            }
        });

        // Listener za kontakt adresu
        ISBNField.textProperty().addListener((observableValue, o, n) -> {
            if (validnost(n)) {
                ISBNField.getStyleClass().removeAll("poljeNijeIspravno");
                ISBNField.getStyleClass().add("poljeIspravno");
            } else {
                ISBNField.getStyleClass().removeAll("poljeIspravno");
                ISBNField.getStyleClass().add("poljeNijeIspravno");
            }
        });

        ISBNField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!ISBNField.isFocused()) {
                    //Kombinacija empty string validatora i
                    // predicate validatora koji poziva metodu validnost
                    Validator validator = Validator.createPredicateValidator((Predicate<String>) s -> validnost2(s),
                            "Neispravna kontakt adresa!");
                    validation.registerValidator(ISBNField, validator);
                } else {
                    // Hack sa controlsFX bitbucketa (u sustini registrira prazan validator ako komponenta nije
                    // fokusirana
                    validation.registerValidator(ISBNField, false, (Control c, String s) ->
                            ValidationResult.fromErrorIf(c, "", false));
                }
            }
        });


        // Listener za datum rodjenja
        datumIzdanjaField.getEditor().textProperty().addListener((observableValue, s, t1) -> {
            if (ispravanDatum(t1)) {
                datumIzdanjaField.getStyleClass().removeAll("poljeNijeIspravno");
                datumIzdanjaField.getStyleClass().add("poljeIspravno");
            } else {
                datumIzdanjaField.getStyleClass().removeAll("poljeIspravno");
                datumIzdanjaField.getStyleClass().add("poljeNijeIspravno");
            }
        });

        datumIzdanjaField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!datumIzdanjaField.isFocused()) {
                    //Kombinacija empty string validatora i
                    // predicate validatora koji poziva metodu validnost
                    Validator validator = Validator.combine(
                            Validator.createEmptyValidator("Datum rođenja ne može biti prazan!"),
                            Validator.createPredicateValidator((Predicate<LocalDate>) localDate -> {
                                LocalDate ld = datumIzdanjaField.getValue();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                String strDate = ld.format(formatter);
                                return ispravanDatum(strDate);
                            }, "Neispravan datum!")
                    );
                    validation.registerValidator(datumIzdanjaField, true, validator);
                } else {
                    // Hack sa controlsFX bitbucketa (u sustini registrira prazan validator ako komponenta nije
                    // fokusirana
                    validation.registerValidator(datumIzdanjaField, false, (Control c, LocalDate s) ->
                            ValidationResult.fromErrorIf(c, "", false));
                }
            }
        });

    }
}