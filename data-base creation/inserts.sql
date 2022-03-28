INSERT INTO project.pracowniki (id_pracownika, nazwisko, imie, pozycja, telefon, email, adres, "kodPocztowy", miasto) VALUES (1, 'asd', 'asd', 'manadzer', '123456757', 'asd@go.com', 'asdas', '12-111', 'krk');

INSERT INTO project.logowanie (id_pracownika, login, haslo) VALUES (1, 'asd', '123');

INSERT INTO project.produkty (id_produktu, "nazwaProduktu", id_nadawcy, id_kategorii, cena, id_magazynu, przerwane) VALUES (63, 'czapka', 1, 2, 200, 2, false);
INSERT INTO project.produkty (id_produktu, "nazwaProduktu", id_nadawcy, id_kategorii, cena, id_magazynu, przerwane) VALUES (1, 'telefon', 1, 1, 100, 2, false);
INSERT INTO project.produkty (id_produktu, "nazwaProduktu", id_nadawcy, id_kategorii, cena, id_magazynu, przerwane) VALUES (3, 'stól', 1, 3, 100, 2, false);

INSERT INTO project.kategorie (id_kategorii, "nazwaKategorii") VALUES (1, 'Elektronika');
INSERT INTO project.kategorie (id_kategorii, "nazwaKategorii") VALUES (2, 'Moda');
INSERT INTO project.kategorie (id_kategorii, "nazwaKategorii") VALUES (3, 'Dom');

INSERT INTO project.magazyny (id_magazynu, wlasciciel, telefon, email, adres, "kodPocztowy", miasto, przerwane) VALUES (5, 'Arkas', '123456789', 'asd@fdas.com', 'asdf', '12-123', 'adsf', false);
INSERT INTO project.magazyny (id_magazynu, wlasciciel, telefon, email, adres, "kodPocztowy", miasto, przerwane) VALUES (6, 'Bishop', '123456789', 'asdf@asdf.com', 'adf', '12-123', 'sdfa', false);
INSERT INTO project.magazyny (id_magazynu, wlasciciel, telefon, email, adres, "kodPocztowy", miasto, przerwane) VALUES (3, 'MediaMrkt', '123456788', 'asdf@mail.com', 'asdfa', '12-123', 'asdf', false);

INSERT INTO project.nadawcy (id_nadawcy, "nazwaFirmy", email, adres, "kodPocztowy", miasto, telefon, przerwane) VALUES (1, 'ALPACH', 'alpach@email.com', 'Reumonta 17', '12-123', 'Warszawa', '123456781', false);
INSERT INTO project.nadawcy (id_nadawcy, "nazwaFirmy", email, adres, "kodPocztowy", miasto, telefon, przerwane) VALUES (2, 'AHGSD', 'hasd@mail.com', 'ASdas', '12-123', 'safasd', '142134123', false);
INSERT INTO project.nadawcy (id_nadawcy, "nazwaFirmy", email, adres, "kodPocztowy", miasto, telefon, przerwane) VALUES (11, 'asdfas', 'asdfas@asdf.com', 'fasfd', '12-123', 'asdfasd', '123456789', true);
INSERT INTO project.nadawcy (id_nadawcy, "nazwaFirmy", email, adres, "kodPocztowy", miasto, telefon, przerwane) VALUES (10, 'safas', 'asdf@mail.com', 'asdfasdf', '12-123', 'asfasdf', '123456789', true);
INSERT INTO project.nadawcy (id_nadawcy, "nazwaFirmy", email, adres, "kodPocztowy", miasto, telefon, przerwane) VALUES (9, 'AFRA', 'asdf@asdf.com', 'asdf', '12-123', 'asdfa', '123456789', false);

INSERT INTO project.spedytor (id_spedytora, "nazwaFirmy", telefon, przerwane) VALUES (1, 'DASA', '432156788', false);
INSERT INTO project.spedytor (id_spedytora, "nazwaFirmy", telefon, przerwane) VALUES (2, 'ASDSA', '231412432', false);
INSERT INTO project.spedytor (id_spedytora, "nazwaFirmy", telefon, przerwane) VALUES (5, 'asdfasd', '123456789', true);
INSERT INTO project.spedytor (id_spedytora, "nazwaFirmy", telefon, przerwane) VALUES (6, 'Kurier', '123456789', true);


