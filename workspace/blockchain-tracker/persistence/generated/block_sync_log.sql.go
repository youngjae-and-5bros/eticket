// Code generated by sqlc. DO NOT EDIT.
// versions:
//   sqlc v1.21.0
// source: block_sync_log.sql

package persistence

import (
	"context"
)

const createSyncLog = `-- name: CreateSyncLog :exec
INSERT INTO
    block_sync_log (` + "`" + `lower_block` + "`" + `, ` + "`" + `upper_block` + "`" + `)
VALUES
    (?, ?)
`

type CreateSyncLogParams struct {
	LowerBlock int64
	UpperBlock int64
}

func (q *Queries) CreateSyncLog(ctx context.Context, arg CreateSyncLogParams) error {
	_, err := q.db.ExecContext(ctx, createSyncLog, arg.LowerBlock, arg.UpperBlock)
	return err
}

const getLatestSyncLog = `-- name: GetLatestSyncLog :one
SELECT
    block_sync_id, lower_block, upper_block, sync_time
FROM
    block_sync_log
ORDER BY
    ` + "`" + `block_sync_id` + "`" + ` DESC
LIMIT
    1
`

func (q *Queries) GetLatestSyncLog(ctx context.Context) (BlockSyncLog, error) {
	row := q.db.QueryRowContext(ctx, getLatestSyncLog)
	var i BlockSyncLog
	err := row.Scan(
		&i.BlockSyncID,
		&i.LowerBlock,
		&i.UpperBlock,
		&i.SyncTime,
	)
	return i, err
}
