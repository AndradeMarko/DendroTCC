package br.com.ufpr.dendrodata.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class DendroDataMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE projeto ADD COLUMN amostra TEXT");

        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //Criar Nova Tabela;
            //Pegar dados do arquivo _Impl
            database.execSQL("CREATE TABLE IF NOT EXISTS `projeto_novo`" +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "`codigo` TEXT," +
                    "`fazenda` TEXT," +
                    "`municipio` TEXT," +
                    "`especie` TEXT," +
                    "`area` TEXT," +
                    "`tamanho` TEXT," +
                    "`quantidade` TEXT," +
                    "`descricao TEXT`)");
            //Copiar Dados da Tabela Antiga para a Nova;
            database.execSQL("INSERT INTO projeto_novo (id, codigo, fazenda, municipio, especie, area, tamanho, quantidade, descricao)" +
                    "SELECT id, codigo, fazenda, municipio, especie, area, tamanho, quantidade, descricao FROM projeto");
            //Remover Tabela Antiga;
            database.execSQL("DROP TABLE projeto");
            //Renomear Tabela nova com nome da Tabela antiga;
            database.execSQL("ALTER TABLE projeto_novo RENAME TO projeto");
        }
    };


    static final Migration[] ALL_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3};
}
