CREATE TABLE movies (id BIGSERIAL PRIMARY KEY NOT NULL,
                     title text NOT NULL,
                     duration integer NOT NULL);

CREATE TABLE sessions (id BIGSERIAL PRIMARY KEY NOT NULL,
                       movie_id bigint REFERENCES movies (id),
                       start_time TIMESTAMP NOT NULL,
                       price numeric(6, 2) NOT NULL);

CREATE TABLE tickets (id BIGSERIAL PRIMARY KEY NOT NULL,
                      session_id bigint REFERENCES sessions (id));

INSERT INTO movies(title, duration)
VALUES ('Film_1', 90),
       ('Film_2', 80),
       ('Film_3', 120),
       ('Film_4', 175);

INSERT INTO sessions(movie_id, start_time, price)
VALUES (1, '01.06.2022 10:00:00', 450.00),
       (2, '01.06.2022 11:00:00', 250.00),
       (3, '01.06.2022 14:30:00', 310.00),
       (4, '01.06.2022 15:10:00', 200.00),
       (1, '01.06.2022 19:00:00', 550.00),
       (3, '01.06.2022 20:45:00', 370.00),
       (2, '01.06.2022 23:00:00', 550.00);

INSERT INTO tickets(session_id)
VALUES (1),
       (1),
       (1),
       (2),
       (2),
       (3),
       (3),
       (3),
       (3),
       (4),
       (5),
       (5),
       (6),
       (6),
       (6),
       (7),
       (7),
       (7),
       (7),
       (7);

-- query 1
SELECT m1.id,
       m1.title,
       s1.start_time,
       m1.duration,
       tmp.id,
       tmp.title,
       tmp.start_time,
       tmp.duration
FROM sessions s1
JOIN movies m1 ON m1.id = s1.movie_id
JOIN
  (SELECT m2.id,
          m2.title,
          s2.start_time,
          m2.duration,
          s2.id AS session_id
   FROM sessions s2
   JOIN movies m2 ON m2.id = s2.movie_id) tmp ON tmp.session_id != s1.id
AND tmp.start_time BETWEEN s1.start_time AND s1.start_time + (m1.duration || ' minutes')::interval
ORDER BY s1.start_time,
         tmp.start_time;

-- query 2
SELECT tbl.title,
       tbl.start_time AS film1_start_time,
       tbl.duration,
       s.start_time AS film2_start_time,
       tbl.b_interval AS break_duration
FROM
  (SELECT tmp_tbl_1.id AS session_id,
          tmp_tbl_1.title,
          tmp_tbl_1.duration,
          tmp_tbl_1.start_time,
          min(tmp_tbl_1.between_interval) b_interval
   FROM
     (SELECT tmp1.*,
             tmp2.start_time - (tmp1.start_time + (tmp1.duration || ' minutes')::interval) between_interval
      FROM
        (SELECT m1.title,
                m1.duration,
                s1.*
         FROM sessions s1
         JOIN movies m1 ON m1.id = s1.movie_id) tmp1
      JOIN
        (SELECT *
         FROM sessions s2) tmp2 ON tmp2.start_time > tmp1.start_time) tmp_tbl_1
   GROUP BY tmp_tbl_1.id,
            tmp_tbl_1.title,
            tmp_tbl_1.start_time,
            tmp_tbl_1.duration
   HAVING min(tmp_tbl_1.between_interval) >= ('30 minutes')::interval) tbl
JOIN sessions s ON s.start_time = tbl.start_time + (tbl.duration || ' minutes')::interval + tbl.b_interval
ORDER BY tbl.b_interval DESC;

-- query 3
WITH out_tbl AS
  (WITH tbl AS
     (SELECT m.id AS m_id,
             m.title,
             s.id AS s_id,
             s.price,
             t.id AS t_id
      FROM tickets t
      JOIN sessions s ON s.id = t.session_id
      JOIN movies m ON m.id = s.movie_id) SELECT DISTINCT tbl.title,
                                                          tbl2.viewers,
                                                          tbl3.avg_viewers,
                                                          tbl4.box_office
   FROM tbl
   JOIN
     (SELECT m_id,
             count(t_id) AS viewers
      FROM tbl
      GROUP BY m_id) tbl2 ON tbl2.m_id = tbl.m_id
   JOIN
     (SELECT in_tbl.m_id,
             avg(in_tbl.viewers)::numeric(6, 2) AS avg_viewers
      FROM
        (SELECT m_id,
                s_id,
                count(t_id) AS viewers
         FROM tbl
         GROUP BY m_id,
                  s_id) in_tbl
      GROUP BY in_tbl.m_id) tbl3 ON tbl3.m_id = tbl.m_id
   JOIN
     (SELECT m_id,
             sum(price) AS box_office
      FROM tbl
      GROUP BY m_id) tbl4 ON tbl4.m_id = tbl.m_id
   ORDER BY tbl4.box_office)
SELECT *
FROM out_tbl
UNION
SELECT 'Итого' AS title,
       sum(out_tbl.viewers) AS viewers,
       avg(out_tbl.avg_viewers)::numeric(6, 2) AS avg_viewers,
       sum(out_tbl.box_office) AS box_office
FROM out_tbl
ORDER BY box_office;

-- query 4
WITH tbl AS
  (SELECT s.id AS session_id,
          s.start_time,
          s.price,
          t.id AS ticket_id
   FROM sessions s
   JOIN tickets t ON t.session_id = s.id)
SELECT *
FROM
  (SELECT 'с 09:00 до 15:00' AS period,
          count(ticket_id) viewers,
          sum(price) box_office
   FROM tbl
   WHERE (start_time)::TIME >= ('9 hours')::interval
     AND (start_time)::TIME < ('15 hours')::interval
   UNION SELECT 'с 15:00 до 18:00' AS period,
                count(ticket_id) viewers,
                sum(price) box_office
   FROM tbl
   WHERE (start_time)::TIME >= ('15 hours')::interval
     AND (start_time)::TIME < ('18 hours')::interval
   UNION SELECT 'с 18:00 до 21:00' AS period,
                count(ticket_id) viewers,
                sum(price) box_office
   FROM tbl
   WHERE (start_time)::TIME >= ('18 hours')::interval
     AND (start_time)::TIME < ('21 hours')::interval
   UNION SELECT 'с 21:00 до 00:00' AS period,
                count(ticket_id) viewers,
                sum(price) box_office
   FROM tbl
   WHERE (start_time)::TIME >= ('21 hours')::interval ) out_tbl
ORDER BY out_tbl.period;