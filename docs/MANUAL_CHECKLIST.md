# Manual regression checklist (prototype)

- [ ] Connect to camera Wi‑Fi and press `Connect`.
- [ ] Verify state reaches `CameraReady`.
- [ ] Verify session flags are shown.
- [ ] Start live and verify `Buffering -> Playing`.
- [ ] Stop live and verify `Stopped`.
- [ ] Change at least 3 settings and verify values are updated.
- [ ] Start record and verify recording state.
- [ ] Poll record status and verify status is still recording.
- [ ] Stop record and verify recording state is false.
- [ ] Take photo and verify file list refreshes.
- [ ] Refresh files and next page works.
- [ ] Download file and verify progress reaches 100%.
- [ ] Open downloaded file.
- [ ] Share downloaded file.
- [ ] Export debug log.
