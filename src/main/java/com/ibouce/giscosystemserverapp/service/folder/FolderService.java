package com.ibouce.giscosystemserverapp.service.folder;


import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;

import java.util.List;

public interface FolderService {

    FolderModel saveFolderToUser(FolderModel folder) throws Exception;

    FolderModel getFolderForUser(FolderModel folder);

    List<FolderModel> getNodesOfFolder(Long id);

    List<FolderModel> getTreeOfFolder(Long id);

    String getFolderPath(Long id);

    FolderModel getParentOfFolder(Long id);

}
