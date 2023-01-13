package com.ibouce.giscosystemserverapp.service.document;


import com.ibouce.giscosystemserverapp.entity.document.DocumentModel;
import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    //DocumentModel saveDocumentToFolder(DocumentModel document) throws Exception;

    List<DocumentModel> getDocumentsOfFolder(Long id);

    DocumentModel saveDocumentToFolder(List<MultipartFile> files, UserModel user, FolderModel folder) throws Exception;
}
