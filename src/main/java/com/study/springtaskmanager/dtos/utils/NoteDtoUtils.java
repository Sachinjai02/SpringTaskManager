package com.study.springtaskmanager.dtos.utils;

import com.study.springtaskmanager.dtos.NoteDto;
import com.study.springtaskmanager.dtos.TaskDto;
import com.study.springtaskmanager.entities.NoteEntity;
import com.study.springtaskmanager.entities.TaskEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NoteDtoUtils {

    public static NoteEntity convertNoteDtoToNoteEntity(NoteDto noteDto) {
        return convertNoteDtoToNoteEntity(Collections.singletonList(noteDto)).get(0);
    }
    public static NoteDto convertNoteEntityToDto(NoteEntity noteEntity) {
        return convertNoteEntityToDto(Collections.singletonList(noteEntity)).get(0);
    }

    public static List<NoteDto> convertNoteEntityToDto(List<NoteEntity> notes) {
        return notes.stream().map(note -> {
            //TODO: simplify this by objectMappers
            NoteDto noteDto = new NoteDto();
            noteDto.setBody(note.getBody());
            noteDto.setId(note.getId());
            return noteDto;
        }).collect(Collectors.toList());
    }

    public static List<NoteEntity> convertNoteDtoToNoteEntity(List<NoteDto> notesDto) {
        return notesDto.stream().map(note -> {
            //TODO: simplify this by objectMappers
            NoteEntity noteEntity = new NoteEntity();
            noteEntity.setBody(note.getBody());
            return noteEntity;
        }).collect(Collectors.toList());
    }
}
