FROM postgres:11
ENV POSTGRES_USER admin
ENV POSTGRES_DB mailing_list
ENV POSTGRES_PASSWORD admin123
ENV PGDATA /var/lib/postgresql/pgdata
RUN echo "host    all    all    0.0.0.0/0    md5" >> /var/lib/postgresql/data/pg_hba.conf
EXPOSE 5432
VOLUME ["/var/lib/postgresql/data", "/var/log/postgresql"]
CMD ["postgres"]