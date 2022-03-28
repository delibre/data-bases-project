-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE VIEW project.informacjaProdukt
AS
select  p.id_produktu, p."nazwaProduktu", p.cena, p.przerwane, k.id_kategorii, k."nazwaKategorii",
        m.id_magazynu, m.wlasciciel, m.telefon as telefon_magazynu, m.email as email_magazynu, m.adres as adres_magazynu, m."kodPocztowy" as "kodPocztowy_magazynu", m.miasto as miasto_magazynu,
        n.id_nadawcy, n."nazwaFirmy", n.email as email_nadawcy, n.adres as adres_nadawcy, n."kodPocztowy" as "kodPocztowy_nadawcy", n.miasto as miasto_nadawcy, n.telefon as telefon_nadawcy from project.produkty p
                        join project.kategorie k using (id_kategorii)
                        join project.magazyny m using (id_magazynu)
                        join project.nadawcy n using (id_nadawcy);
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
               
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE VIEW project.informacjaZamowienia
AS
select p."nazwaProduktu", p.cena as cena_produktu, p.id_produktu, z.id_zamowienia, z. id_klienta, z."dataWysylki",
       z."dataZamowienia", z."wyslanePrzez", z."adresWysylki", z."miastoWysylki", z."kodPocztowyWysylki", z."przetworzonePrzez", z.cena, z.opis,
       k.id_kategorii, k."nazwaKategorii" from project."szczegolyZamowienia" sz
                        join project.zamowienia z using (id_zamowienia)
                        join project.produkty p using (id_produktu)
                        join project.kategorie k using (id_kategorii);