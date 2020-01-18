package eu.cwsfe.monitoring.history;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public HistoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(History history) {
        Object[] params = new Object[2];
        params[0] = history.getCode();
        params[1] = history.getDescription();
        jdbcTemplate.update("insert into history(id, code, description) values (nextval('history_id_seq'), ?, ?)", params);
    }

}
