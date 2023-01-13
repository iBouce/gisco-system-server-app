package com.ibouce.giscosystemserverapp.repository.document;

import com.ibouce.giscosystemserverapp.entity.document.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentModel, Long> {

    List<DocumentModel> findDocumentByFolderId(Long id);

    List<DocumentModel> findByNameAndUserAndFolder(String documentName, Long userId, Long folderParent);

}
