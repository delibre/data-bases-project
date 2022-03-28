create function project.zalogujKlienta(logink character varying, haslok character varying) returns boolean
    language plpgsql
as
$$
DECLARE
        existsCheckData BOOLEAN;
    BEGIN
        SELECT EXISTS (select lk.login, lk.haslo
        from project.logowanie_klienta lk
        where lk.login = loginK and lk.haslo = hasloK)
        into existsCheckData;

        if (SELECT existsCheckData = TRUE) THEN
            return true;
        else
           RAISE EXCEPTION 'Podałeś zły login lub  hasło, sprawdź swoje dane i sprobuj ponownie!';
        end if;
    END;
$$;
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
create function project.zalogujMenadzera(loginm character varying, haslom character varying) returns boolean
    language plpgsql
as
$$
DECLARE
        existsCheckData BOOLEAN;
    BEGIN
        select exists(select ml.login, ml.haslo from project.logowanie ml where ml.login = loginM and ml.haslo = hasloM) into existsCheckData;
        if (select existsCheckData = TRUE) THEN
            return true;
        else
           RAISE EXCEPTION 'Podałeś zły login lub  hasło, sprawdź swoje dane i sprobuj ponownie!';
        end if;
    END;
$$;
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
create function project.dodajKlienta(imiek character varying, nazwiskok character varying, mailk character varying, numerk character varying, adresk character varying, miastok character varying, kodPk character varying, logink character varying, haslok character varying) returns boolean
    language plpgsql
as
$$
DECLARE
    existsCheck BOOLEAN;
    id          RECORD;
BEGIN
    SELECT EXISTS (select k.telefon from project.klienci k where k.telefon = numerK) INTO existsCheck;
    IF (SELECT existsCheck = FALSE) THEN
        insert into project.klienci (imie, nazwisko, email, telefon, adres, miasto, "kodPocztowy") values (imieK, nazwiskoK, mailK, numerK, adresK, miastoK, kodPK);
        SELECT id_klienta INTO id FROM project.klienci WHERE telefon = numerK;
        INSERT INTO project.logowanie_klienta (id_klienta, login, haslo) VALUES (id.id_klienta, loginK, hasloK);
    ELSE
        RAISE EXCEPTION 'Klient z tym samym numerem już istnieje!';
    END IF;
    RETURN TRUE;
END;
$$;
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
create function project.dodajProdukt(nazwap character varying, cenap double precision, kategoriap int, nadawcap int, magazynp int) returns boolean
    language plpgsql
as
$$
BEGIN
    insert into project.produkty ("nazwaProduktu", id_nadawcy, id_kategorii, cena, id_magazynu) values (nazwaP, nadawcaP, kategoriaP, cast(cenaP as decimal), magazynP);
    RETURN TRUE;
END;
$$;
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
create function project.dodajMagazyn(wlascicielm character varying, telefonm character varying, emailm character varying, adresm character varying, kodPm character varying, miastom character varying) returns boolean
    language plpgsql
as
$$
DECLARE
    existsCheck BOOLEAN;
BEGIN
    SELECT EXISTS (select m.wlasciciel from project.magazyny m where m.wlasciciel = wlascicielM) INTO existsCheck;
    IF (SELECT existsCheck = FALSE) THEN
        insert into project.magazyny (wlasciciel, telefon, email, adres, "kodPocztowy", miasto) values (wlascicielM, telefonM, emailM, adresM, kodPM, miastoM);
    ELSE
        RAISE EXCEPTION 'Magazyn już istnieje';
    END IF;
    RETURN TRUE;
END;
$$;
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
create function project.dodajNadawce(nazwan character varying, telefonn character varying, emailn character varying, adresn character varying, kodPn character varying, miaston character varying) returns boolean
    language plpgsql
as
$$
DECLARE
    existsCheck BOOLEAN;
BEGIN
    SELECT EXISTS (select n."nazwaFirmy" from project.nadawcy n where n."nazwaFirmy" = nazwaN) INTO existsCheck;
    IF (SELECT existsCheck = FALSE) THEN
        insert into project.nadawcy ("nazwaFirmy", email, adres, "kodPocztowy", miasto, telefon) values (nazwaN, emailN, adresN, kodPn, miastoN, telefonN);
    ELSE
        RAISE EXCEPTION 'Nadawca już istnieje';
    END IF;
    RETURN TRUE;
END;
$$;