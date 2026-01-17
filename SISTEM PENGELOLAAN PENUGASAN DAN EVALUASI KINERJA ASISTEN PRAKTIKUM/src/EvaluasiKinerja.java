public class EvaluasiKinerja extends Evaluasi {
    private int disiplin;
    private int tanggungJawab;
    private int komunikasi;

    public EvaluasiKinerja(int disiplin, int tanggungJawab, int komunikasi) {
        this.disiplin = disiplin;
        this.tanggungJawab = tanggungJawab;
        this.komunikasi = komunikasi;
    }

    @Override
    public int hitungNilai() {
        nilai = (disiplin + tanggungJawab + komunikasi) / 3;
        return nilai;
    }

    @Override
    public String kategori() {
        if (nilai >= 85) return "Sangat Baik";
        else if (nilai >= 70) return "Baik";
        else return "Perlu Evaluasi";
    }

    public String getStatusKelayakan() {
        return (nilai >= 70) ? "LAYAK DIPERTAHANKAN" : "PERLU PEMBINAAN";
    }
}