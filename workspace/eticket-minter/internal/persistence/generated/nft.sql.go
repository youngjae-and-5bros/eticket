// Code generated by sqlc. DO NOT EDIT.
// versions:
//   sqlc v1.22.0
// source: nft.sql

package persistence

import (
	"context"
	"database/sql"
	"strings"
	"time"
)

const bulkMarkAsMinted = `-- name: BulkMarkAsMinted :exec

UPDATE ` + "`" + `reservation` + "`" + `
SET ` + "`" + `status` + "`" + ` = 'MINTED'
WHERE
    ` + "`" + `reservation_id` + "`" + ` IN (/*SLICE:reservations*/?)
`

func (q *Queries) BulkMarkAsMinted(ctx context.Context, reservations []int32) error {
	query := bulkMarkAsMinted
	var queryParams []interface{}
	if len(reservations) > 0 {
		for _, v := range reservations {
			queryParams = append(queryParams, v)
		}
		query = strings.Replace(query, "/*SLICE:reservations*/?", strings.Repeat(",?", len(reservations))[1:], 1)
	} else {
		query = strings.Replace(query, "/*SLICE:reservations*/?", "NULL", 1)
	}
	_, err := q.db.ExecContext(ctx, query, queryParams...)
	return err
}

const confirmedReservations = `-- name: ConfirmedReservations :many

WITH
    ` + "`" + `confirmed_reservations` + "`" + ` AS (
        SELECT
            r.` + "`" + `reservation_id` + "`" + `,
            r.` + "`" + `user_id` + "`" + `,
            r.` + "`" + `performance_schedule_id` + "`" + `,
            r.` + "`" + `seat_id` + "`" + `,
            ps.` + "`" + `start_date_time` + "`" + `
        FROM ` + "`" + `reservation` + "`" + ` r
            INNER JOIN ` + "`" + `performance_schedule` + "`" + ` ps ON r.` + "`" + `performance_schedule_id` + "`" + ` = ps.` + "`" + `performance_schedule_id` + "`" + `
        WHERE
            r.` + "`" + `status` + "`" + ` = 'SOLDOUT'
            AND DATE(ps.` + "`" + `start_date_time` + "`" + `) <= NOW() + INTERVAL 1 DAY
    )
SELECT
    r.` + "`" + `reservation_id` + "`" + `,
    r.` + "`" + `performance_schedule_id` + "`" + `,
    r.` + "`" + `start_date_time` + "`" + ` as ` + "`" + `start_time` + "`" + `,
    r.` + "`" + `user_id` + "`" + `,
    u.` + "`" + `wallet_address` + "`" + `,
    r.` + "`" + `seat_id` + "`" + `
FROM
    ` + "`" + `confirmed_reservations` + "`" + ` r
    INNER JOIN ` + "`" + `user` + "`" + ` u ON r.` + "`" + `user_id` + "`" + ` = u.` + "`" + `id` + "`" + `
LIMIT ?
`

type ConfirmedReservationsRow struct {
	ReservationID         int32
	PerformanceScheduleID int32
	StartTime             time.Time
	UserID                int32
	WalletAddress         sql.NullString
	SeatID                int32
}

func (q *Queries) ConfirmedReservations(ctx context.Context, limit int32) ([]ConfirmedReservationsRow, error) {
	rows, err := q.db.QueryContext(ctx, confirmedReservations, limit)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	var items []ConfirmedReservationsRow
	for rows.Next() {
		var i ConfirmedReservationsRow
		if err := rows.Scan(
			&i.ReservationID,
			&i.PerformanceScheduleID,
			&i.StartTime,
			&i.UserID,
			&i.WalletAddress,
			&i.SeatID,
		); err != nil {
			return nil, err
		}
		items = append(items, i)
	}
	if err := rows.Close(); err != nil {
		return nil, err
	}
	if err := rows.Err(); err != nil {
		return nil, err
	}
	return items, nil
}

const markAsMinted = `-- name: MarkAsMinted :exec

UPDATE ` + "`" + `reservation` + "`" + `
SET ` + "`" + `status` + "`" + ` = 'MINTED'
WHERE ` + "`" + `reservation_id` + "`" + ` = ?
`

func (q *Queries) MarkAsMinted(ctx context.Context, reservationID int32) error {
	_, err := q.db.ExecContext(ctx, markAsMinted, reservationID)
	return err
}