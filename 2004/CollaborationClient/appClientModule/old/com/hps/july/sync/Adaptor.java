package old.com.hps.july.sync;

/**
 * @author Dmitry Dneprov
 *
 * Абстрактный адаптер для доступа к таблице в БД
 * обеспечивает обновление записи
 */
public interface Adaptor {
    /// Обновление объекта в NRI
	public abstract boolean updateObjectNRI(BusinessObject argObj);

    /// Установка связи с существующим объектом NRI
    public abstract boolean makeRelationNRI(BusinessObject argObj);

    /// Удаление объекта в NRI
	public abstract boolean deleteObjectNRI(BusinessObject argObj);

    /// Поиск изменений в JOIN_DB и их перенос в NRI
    public abstract void findChangesJOIN_DBUpdateNRI(Query argQry);

    /// Поиск записей, удаленных в JOIN_DB и их удаление в NRI
    public abstract void findDeletedJOIN_DBDeleteNRI(Query argQry);
}