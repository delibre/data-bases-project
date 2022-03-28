CREATE OR REPLACE FUNCTION valid_data ()
    RETURNS TRIGGER
    LANGUAGE plpgsql
    AS $$
    BEGIN
    IF LENGTH(NEW.imie || NEW.nazwisko) = 0 THEN
        RAISE EXCEPTION 'Nazwisko lub imie nie moze byc puste.';
                RETURN NULL; --Anulujemy
    END IF;

    RETURN NEW;  --Akceputacja modyfikacji
    END;
    $$;

CREATE TRIGGER client_valid
    AFTER INSERT OR UPDATE  ON project.klienci
    FOR EACH ROW EXECUTE PROCEDURE valid_data();
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION valid_category ()
    RETURNS TRIGGER
    LANGUAGE plpgsql
    AS $$
    BEGIN
    IF LENGTH(NEW."nazwaKategorii") = 0 THEN
        RAISE EXCEPTION 'Nazwa kategorii nie moze byc pusta.';
                RETURN NULL; --Anulujemy
    END IF;

    RETURN NEW;
    END;
    $$;

CREATE TRIGGER category_valid
    AFTER INSERT OR UPDATE  ON project.kategorie
    FOR EACH ROW EXECUTE PROCEDURE valid_category();
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION valid_login_worker ()
    RETURNS TRIGGER
    LANGUAGE plpgsql
    AS $$
    BEGIN
    IF LENGTH(NEW.login || NEW.haslo) = 0 THEN
        RAISE EXCEPTION 'Login lub hasło nie mogą byc puste.';
                RETURN NULL; --Anulujemy
    END IF;

    RETURN NEW;
    END;
    $$;

CREATE TRIGGER login_worker_valid
    AFTER INSERT OR UPDATE  ON project.logowanie
    FOR EACH ROW EXECUTE PROCEDURE valid_login_worker();
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION valid_login_client ()
    RETURNS TRIGGER
    LANGUAGE plpgsql
    AS $$
    BEGIN
    IF LENGTH(NEW.login || NEW.haslo) = 0 THEN
        RAISE EXCEPTION 'Login lub hasło nie mogą byc puste.';
                RETURN NULL; --Anulujemy
    END IF;

    RETURN NEW;
    END;
    $$;

CREATE TRIGGER login_client_valid
    AFTER INSERT OR UPDATE  ON project.logowanie
    FOR EACH ROW EXECUTE PROCEDURE valid_login_client();