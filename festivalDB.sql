#CREATE DATABASE festival;
#USE festival;

DROP TABLE IF EXISTS tracks;

CREATE TABLE tracks (
TrackURI varchar(255) NOT NULL,
TrackName varchar(255) NOT NULL,
ArtistURIs varchar(255) NOT NULL,
ArtistNames varchar(255) NOT NULL,
AlbumURI varchar(255) NOT NULL,
AlbumName varchar(255) NOT NULL,
AlbumArtistURIs varchar(255) NOT NULL,
AlbumArtistNames varchar(255) NOT NULL,
AlbumReleaseDate varchar(255) NOT NULL,
AlbumImageURL varchar(255) NOT NULL,
DiscNumber varchar(255) NOT NULL,
TrackNumber varchar(255) NOT NULL,
TrackDuration varchar(255) NOT NULL,
TrackPreviewURL varchar(255),
Explicit varchar(255) NOT NULL,
Popularity varchar(255) NOT NULL,
AddedBy varchar(255) NOT NULL,
AddedAt varchar(255) NOT NULL,
PRIMARY KEY (TrackURI)	
);