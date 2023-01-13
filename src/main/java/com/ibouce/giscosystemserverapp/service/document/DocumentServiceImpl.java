package com.ibouce.giscosystemserverapp.service.document;

import com.ibouce.giscosystemserverapp.entity.document.DocumentModel;
import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import com.ibouce.giscosystemserverapp.repository.document.DocumentRepository;
import com.ibouce.giscosystemserverapp.service.folder.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    FolderService folderService;


    @Value("${directory.root}")
    String root;

    @Override
    public DocumentModel saveDocumentToFolder(List<MultipartFile> files, UserModel user, FolderModel folder) throws Exception {
        DocumentModel document = new DocumentModel();
        List<String> filenames = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());

            System.out.println("Save Document !");
            documentRepository.save(new DocumentModel(null, filename, user, folder));

            System.out.println("Upload Document to file System !");
            Path fileStorage = get(root + "/" + folderService.getFolderPath(folder.getId()), filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);

        }
        return document;
    }

    @Override
    public List<DocumentModel> getDocumentsOfFolder(Long id) {
        return documentRepository.findDocumentByFolderId(id);
    }


    /*@Override
    public List<DocumentModel> saveDocumentToFolder(List<MultipartFile> files, DocumentModel document) throws Exception {
        List<DocumentModel> currentDocument = this.documentRepository.findByNameAndUserAndFolder(document.getName(), document.getUser().getId(), document.getFolder().getParent());
        if (currentDocument != null) {
            throw new Exception("Document is already present !");
        } else {
            List<String> filenames = new ArrayList<>();
            for (MultipartFile file : files) {
                String filename = StringUtils.cleanPath(file.getOriginalFilename());

                System.out.println("Save Document !");
                document.setName(filename);
                documentRepository.save(document);

                System.out.println("Upload Document to file System !");
                Path fileStorage = get(root + folderService.getFolderPath(document.getFolder().getParent()), filename).toAbsolutePath().normalize();
                copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
                filenames.add(filename);

            }
        }
        return currentDocument;
    }*/



}
