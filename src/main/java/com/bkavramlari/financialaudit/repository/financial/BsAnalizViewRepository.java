package com.bkavramlari.financialaudit.repository.financial;

import com.bkavramlari.financialaudit.domain.financial.BsAnalizView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface BsAnalizViewRepository extends JpaRepository<BsAnalizView, Long> {

    @Query("select new com.bkavramlari.financialaudit.domain.financial.BsAnalizView(sum(baw.alacak), sum(baw.cAlacak), sum(baw.borc), baw.vergiNo) from BsAnalizView baw WHERE baw.hhUploadId = :hhUploadId AND baw.kUploadId = :KUploadId AND baw.bUploadId = :BUploadId group by baw.vergiNo")
    List<BsAnalizView> findByUploadIds(
            @Param("hhUploadId") Long hesapHareketleriUploadId,
            @Param("BUploadId") Long bufferUploadId,
            @Param("KUploadId") Long kitapUploadId);
}