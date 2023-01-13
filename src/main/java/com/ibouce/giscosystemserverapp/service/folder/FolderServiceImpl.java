package com.ibouce.giscosystemserverapp.service.folder;

import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import com.ibouce.giscosystemserverapp.repository.folder.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    @Autowired
    FolderRepository folderRepository;

    @Value("${directory.root}")
    String root;

    @Override
    public FolderModel saveFolderToUser(FolderModel folder) throws Exception {
        FolderModel currentFolder = this.folderRepository.findByNameAndParent(folder.getName(), folder.getParent());
        if (currentFolder != null) {
            throw new Exception("Folder is already present !");
        } else {
            System.out.println("Create Folder !");
            currentFolder = this.folderRepository.save(folder);
            File file = new File(root + "/" + getFolderPath(currentFolder.getParent()) + "/" + currentFolder.getName());
            file.mkdirs();
            System.out.println(file.getName() + " Created in " + root + "/" + getFolderPath(currentFolder.getParent()));
        }
        return currentFolder;
    }

    @Override
    public FolderModel getFolderForUser(FolderModel folder) {
        return folderRepository.findByUserIdAndName(folder.getUser().getId(), folder.getName());
    }

    @Override
    public List<FolderModel> getNodesOfFolder(Long id) {
        return folderRepository.findByParent(id);
    }

    @Override
    public List<FolderModel> getTreeOfFolder(Long id) {
        List<FolderModel> folders = folderRepository.findByUserId(id);
        List<FolderModel> tree = new ArrayList<>();
        for (FolderModel folder : folders) {
            if (tree.contains(folder)) {
                continue;
            }
            tree.add(folder);
            tree.addAll(folderRepository.findByParent(folder.getId()));
        }
        return tree;
    }


    @Override
    public String getFolderPath(Long id) {
        String path = "";
        FolderModel folder = folderRepository.findFolderById(id); //input parent = 3 -> id = 3
        path = path + folder.getName();
        while (folder.getParent() != null) {//2
            FolderModel subFolder = folderRepository.findFolderById(folder.getParent());// 2
            path = path + "/" + subFolder.getName();
            folder = folderRepository.findParentById(subFolder.getId());//2
        }

        String newPath = Stream.iterate(Path.of(path), Path::getParent)
                .takeWhile(Objects::nonNull)
                .map(Path::getFileName).map(Path::toString)
                .collect(Collectors.joining("/"));

        return newPath;
    }


    @Override
    public FolderModel getParentOfFolder(Long id) {
        return folderRepository.findFolderByParent(id);
    }

}
