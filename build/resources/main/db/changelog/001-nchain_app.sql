

create table nchain_app(
id integer PRIMARY KEY autoincrement,
name text not null,
type text CHECK(type IN ('android','ios')) not null,
image text not null,
store text not null,
ratings integer,
size integer,
score numeric);
