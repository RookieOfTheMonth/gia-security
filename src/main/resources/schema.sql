DROP TABLE If Exists emp2;
DROP TABLE If Exists dept2;

CREATE TABLE dept2
(
    deptno INT AUTO_INCREMENT NOT NULL,
    dname  VARCHAR(14),
    loc    VARCHAR(13),
    CONSTRAINT dept2_primary_key PRIMARY KEY (deptno)
);

INSERT INTO dept2(dname, loc, deptno)
VALUES ('財務部', '臺灣台北', 10);
INSERT INTO dept2(dname, loc, deptno)
VALUES ('研發部', '臺灣新竹', 20);
INSERT INTO dept2(dname, loc, deptno)
VALUES ('業務部', '美國紐約', 30);
INSERT INTO dept2(dname, loc, deptno)
VALUES ('生管部', '日本東京', 40);

CREATE TABLE emp2
(
    empno    INT AUTO_INCREMENT NOT NULL,
    ename    VARCHAR(10),
    job      VARCHAR(9),
    hiredate DATE,
    sal      FLOAT,
    comm     FLOAT,
    deptno   INT NOT NULL,
    CONSTRAINT emp2_deptno_fk FOREIGN KEY (deptno) REFERENCES dept2 (deptno),
    CONSTRAINT emp2_empno_pk PRIMARY KEY (empno)
);