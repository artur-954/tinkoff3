package ru.tinkoff.edu.java.scrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.LinkResponse;
import ru.tinkoff.edu.java.scrapper.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.RemoveLinkRequest;
import java.util.ArrayList;
import java.util.NoSuchElementException;
@Validated
@RestController
@RequestMapping(path = "/links",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LinkController {
    @GetMapping("/{id}")
    public ListLinksResponse getAll(@PathVariable long id) {
        return new ListLinksResponse(new ArrayList<>(), 0);
    }
    @PostMapping("/{id}")
    public LinkResponse add(@PathVariable long id, @RequestBody AddLinkRequest request) {
        return new LinkResponse(id, "https://www.oracle.com/");
    }
    @DeleteMapping("/{id}")
    public LinkResponse remove(@PathVariable long id, @RequestBody RemoveLinkRequest request) {
        throw new NoSuchElementException(String.format("Ссылка c таким id={%d} не найдена", id));
    }
}