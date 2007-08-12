
CREATE TABLE "APP.DWZ_SPIELER" (
  "ZPS"                varchar(5)   NOT NULL default '',
  "Mgl_Nr"             char(3)      NOT NULL default '',
  "Status"             char(1)               default NULL,
  "Spielername"        varchar(40)  NOT NULL default '',
  "Geschlecht"         char(1)               default NULL,
  "Spielberechtigung"  char(1)      NOT NULL default '',
  "Geburtsjahr"        smallint       NOT NULL default 0,
  "Letzte_Auswertung"  varchar(6)  default NULL,
  "DWZ"                smallint   default 0,
  "DWZ_Index"          smallint   default 0,
  "FIDE_Elo"           smallint   default 0,
  "FIDE_Titel"         char(4)       default NULL,
  "FIDE_ID"            varchar(8)        default NULL,
  "FIDE_Land"          char(3)       default NULL,
  "SpielernameL"        varchar(40)  NOT NULL default '',
  PRIMARY KEY  ("ZPS","Mgl_Nr")
)