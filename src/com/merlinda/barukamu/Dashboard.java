package com.merlinda.barukamu;

public class Dashboard {
	private String username, password, namaUsaha, cabangIndustri, jenisKomoditi, alamat, namaPemilik, latitude, namaProduk, hargaProduk,detailProduk, gambarProduk, promo;
    private int idUser, idProduk,idAkses, idUsaha;
    private double lat, lng;
    
    
    
	public Dashboard() {
	}
	
	public Dashboard(String username, String password, String namaUsaha,
			String cabangIndustri, String jenisKomoditi, String alamat,
			String namaPemilik, String latitude, String namaProduk,
			String hargaProduk, String detailProduk, String gambarProduk,
			String promo, int idUser, int idProduk, int idAkses, int idUsaha,
			double lat, double lng) {
		super();
		this.username = username;
		this.password = password;
		this.namaUsaha = namaUsaha;
		this.cabangIndustri = cabangIndustri;
		this.jenisKomoditi = jenisKomoditi;
		this.alamat = alamat;
		this.namaPemilik = namaPemilik;
		this.latitude = latitude;
		this.namaProduk = namaProduk;
		this.hargaProduk = hargaProduk;
		this.detailProduk = detailProduk;
		this.gambarProduk = gambarProduk;
		this.promo = promo;
		this.idUser = idUser;
		this.idProduk = idProduk;
		this.idAkses = idAkses;
		this.idUsaha = idUsaha;
		this.lat = lat;
		this.lng = lng;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNamaUsaha() {
		return namaUsaha;
	}
	public void setNamaUsaha(String namaUsaha) {
		this.namaUsaha = namaUsaha;
	}
	public String getCabangIndustri() {
		return cabangIndustri;
	}
	public void setCabangIndustri(String cabangIndustri) {
		this.cabangIndustri = cabangIndustri;
	}
	public String getJenisKomoditi() {
		return jenisKomoditi;
	}
	public void setJenisKomoditi(String jenisKomoditi) {
		this.jenisKomoditi = jenisKomoditi;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getNamaPemilik() {
		return namaPemilik;
	}
	public void setNamaPemilik(String namaPemilik) {
		this.namaPemilik = namaPemilik;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getNamaProduk() {
		return namaProduk;
	}
	public void setNamaProduk(String namaProduk) {
		this.namaProduk = namaProduk;
	}
	public String getHargaProduk() {
		return hargaProduk;
	}
	public void setHargaProduk(String hargaProduk) {
		this.hargaProduk = hargaProduk;
	}
	public String getDetailProduk() {
		return detailProduk;
	}
	public void setDetailProduk(String detailProduk) {
		this.detailProduk = detailProduk;
	}
	public String getGambarProduk() {
		return gambarProduk;
	}
	public void setGambarProduk(String gambarProduk) {
		this.gambarProduk = gambarProduk;
	}
	public String getPromo() {
		return promo;
	}
	public void setPromo(String promo) {
		this.promo = promo;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdProduk() {
		return idProduk;
	}
	public void setIdProduk(int idProduk) {
		this.idProduk = idProduk;
	}
	public int getIdAkses() {
		return idAkses;
	}
	public void setIdAkses(int idAkses) {
		this.idAkses = idAkses;
	}
	public int getIdUsaha() {
		return idUsaha;
	}
	public void setIdUsaha(int idUsaha) {
		this.idUsaha = idUsaha;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
    
    

}
