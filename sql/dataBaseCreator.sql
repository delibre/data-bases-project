create table project.nadawcy
(
    id_nadawcy    serial
        constraint nadawcy_pk
            primary key,
    "nazwaFirmy"  varchar(40),
    email         varchar(40),
    adres         varchar(60),
    "kodPocztowy" varchar(10),
    miasto        varchar(40),
    telefon       varchar(40),
    przerwane     boolean default false not null
);

alter table project.nadawcy
    owner to euoxnnag;

create table project.kategorie
(
    id_kategorii     serial
        constraint kategorie_pk
            primary key,
    "nazwaKategorii" varchar(40)
);

alter table project.kategorie
    owner to euoxnnag;

create table project.magazyny
(
    id_magazynu   serial
        constraint magazyny_pk
            primary key,
    wlasciciel    varchar(10),
    telefon       varchar(15),
    email         varchar(40),
    adres         varchar(60),
    "kodPocztowy" varchar(10),
    miasto        varchar(40),
    przerwane     boolean default false not null
);

alter table project.magazyny
    owner to euoxnnag;

create table project.produkty
(
    id_produktu     serial
        constraint produkty_pk
            primary key,
    "nazwaProduktu" varchar(40),
    id_nadawcy      integer
        constraint produkty_nadawcy_id_nadawcy_fk
            references project.nadawcy,
    id_kategorii    integer
        constraint produkty_kategorie_id_kategorii_fk
            references project.kategorie,
    cena            numeric,
    id_magazynu     integer
        constraint produkty_magazyny_id_magazynu_fk
            references project.magazyny,
    przerwane       boolean default false not null
);

alter table project.produkty
    owner to euoxnnag;

create table project.pracowniki
(
    id_pracownika serial
        constraint pracowniki_pk
            primary key,
    nazwisko      varchar(40),
    imie          varchar(40),
    pozycja       varchar(40),
    telefon       varchar(15),
    email         varchar(40),
    adres         varchar(60),
    "kodPocztowy" varchar(10),
    miasto        varchar(40)
);

alter table project.pracowniki
    owner to euoxnnag;

create table project.spedytor
(
    id_spedytora serial
        constraint spedytor_pk
            primary key,
    "nazwaFirmy" varchar(40),
    telefon      varchar(15),
    przerwane    boolean default false not null
);

alter table project.spedytor
    owner to euoxnnag;

create table project.klienci
(
    id_klienta    serial
        constraint klienci_pk
            primary key,
    imie          varchar(40),
    nazwisko      varchar(40),
    email         varchar(40),
    telefon       varchar(15),
    adres         varchar(60),
    miasto        varchar(40),
    "kodPocztowy" varchar(10)
);

alter table project.klienci
    owner to euoxnnag;

create table project.zamowienia
(
    id_zamowienia        serial
        constraint zamowienia_pk
            primary key,
    id_klienta           integer
        constraint zamowienia_klienci_id_klienta_fk
            references project.klienci,
    "dataZamowienia"     date,
    "dataWysylki"        date,
    "wyslanePrzez"       integer
        constraint zamowienia_spedytor_id_spedytora_fk
            references project.spedytor,
    "adresWysylki"       varchar(60),
    "miastoWysylki"      varchar(60),
    "kodPocztowyWysylki" varchar(6),
    "przetworzonePrzez"  integer
        constraint zamowienia_pracowniki_id_pracownika_fk
            references project.pracowniki,
    opis                 text,
    cena                 numeric
);

alter table project.zamowienia
    owner to euoxnnag;

create table project."szczegolyZamowienia"
(
    id_zamowienia integer not null
        constraint detalizamowienia_zamowienia_id_zamowienia_fk
            references project.zamowienia,
    id_produktu   integer not null
        constraint szczegolyzamowienia_produkty_id_produktu_fk
            references project.produkty,
    cena          numeric,
    ilosc         integer,
    constraint szczegolyzamowienia_pk
        primary key (id_zamowienia, id_produktu)
);

alter table project."szczegolyZamowienia"
    owner to euoxnnag;

create table project.logowanie
(
    id_pracownika serial
        constraint logowanie_pk
            primary key
        constraint logowanie_pracowniki_id_pracownika_fk
            references project.pracowniki,
    login         varchar(20),
    haslo         varchar(20)
);

alter table project.logowanie
    owner to euoxnnag;

create table project.logowanie_klienta
(
    id_klienta serial
        constraint logowanie_klienta_pk
            primary key
        constraint logowanie_klienta_klienci_id_klienta_fk
            references project.klienci,
    login      varchar(40),
    haslo      varchar(40)
);

alter table project.logowanie_klienta
    owner to euoxnnag;

